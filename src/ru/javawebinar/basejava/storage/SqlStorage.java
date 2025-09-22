package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SqlStorage implements Storage {
    private final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.blockExecute("DELETE FROM resume", PreparedStatement::execute);
    }

    @Override
    public void update(Resume r) {
        sqlHelper.transactionExecute(conn -> {
            try (var ps = conn.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid = ?")) {
                ps.setString(1, r.getFullName());
                ps.setString(2, r.getUuid());

                if (ps.executeUpdate() == 0) {
                    throw new NotExistStorageException(r.getUuid());
                }
            }

            try (var ps = conn.prepareStatement("DELETE FROM contact WHERE resume_uuid = ?")) {
                ps.setString(1, r.getUuid());
                ps.execute();
            }

            if (r.getContacts() != null && !r.getContacts().isEmpty()) {
                insertContacts(r, conn);
            }
            return null;
        });
    }

    @Override
    public void save(Resume r) {
        sqlHelper.transactionExecute(conn -> {
            try (var ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?, ?)")) {
                ps.setString(1, r.getUuid());
                ps.setString(2, r.getFullName());
                ps.execute();
            }

            insertContacts(r, conn);
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.blockExecute(
                " SELECT * FROM resume r" +
                        "   LEFT JOIN contact c " +
                        "     ON r.uuid = c.resume_uuid" +
                        "  WHERE r.uuid =?", ps -> {
                    ps.setString(1, uuid);

                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new NotExistStorageException(uuid);
                    }
                    Resume r = new Resume(uuid, rs.getString("full_name"));

                    do {
                        // Если есть контакты, добавить их.
                        getContacts(r, rs);
                    } while (rs.next());

                    return r;
                });
    }


    @Override
    public void delete(String uuid) {
        sqlHelper.blockExecute("DELETE FROM resume WHERE uuid = ?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.blockExecute("""
                SELECT r.*, c.type, c.value
                FROM resume r
                LEFT JOIN contact c ON r.uuid = c.resume_uuid
                ORDER BY r.full_name, r.uuid
                """, ps -> {

            ResultSet rs = ps.executeQuery();
            Map<String, Resume> resumes = new LinkedHashMap<>();

            while (rs.next()) {
                String uuid = rs.getString("uuid");

                // Если такого резюме нет в мапе, добавить его.
                Resume resume = resumes.get(uuid);
                if (resume == null) {
                    resume = new Resume(uuid, rs.getString("full_name"));
                    resumes.put(uuid, resume);
                }

                // Если есть контакты, добавить их.
                getContacts(resume, rs);
            }
            return new ArrayList<>(resumes.values());
        });
    }

    @Override
    public int size() {
        return sqlHelper.blockExecute("SELECT COUNT(*) FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }

    private static void insertContacts(Resume r, Connection conn) throws SQLException {
        try (var ps = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?, ?, ?)")) {
            for (Map.Entry<ContactType, String> el : r.getContacts().entrySet()) {
                ps.setString(1, r.getUuid());
                ps.setString(2, el.getKey().name());
                ps.setString(3, el.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private static void getContacts(Resume r, ResultSet rs) throws SQLException {
        String contactType = rs.getString("type");
        if (contactType != null) {
            r.setContacts(ContactType.valueOf(contactType), rs.getString("value"));
        }
    }
}
