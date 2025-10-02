package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.*;
import java.util.*;

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

            try (var ps = conn.prepareStatement("DELETE FROM section WHERE resume_uuid = ?")) {
                ps.setString(1, r.getUuid());
                ps.execute();
            }

            if (r.getContacts() != null && !r.getContacts().isEmpty()) {
                insertContacts(r, conn);
            }

            if (r.getSections() != null && !r.getSections().isEmpty()) {
                insertSections(r, conn);
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
            insertSections(r, conn);
            return null;
        });

    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.blockExecute(
                "  SELECT * FROM resume r" +
                        "  LEFT JOIN contact c " +
                        "    ON r.uuid = c.resume_uuid" +
                        "  LEFT JOIN section s " +
                        "    ON r.uuid = s.resume_uuid" +
                        " WHERE r.uuid =?", ps -> {

                    ps.setString(1, uuid);
                    var rs = ps.executeQuery();

                    if (!rs.next()) {
                        throw new NotExistStorageException(uuid);
                    }

                    var r = new Resume(uuid, rs.getString("full_name"));

                    do {
                        addContacts(r, rs);
                        addSections(r, rs);
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
        return sqlHelper.blockExecute("SELECT * FROM resume ORDER BY full_name, uuid", ps -> {
            Map<String, Resume> map = new LinkedHashMap<>();
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String uuid = rs.getString("uuid");
                map.put(uuid, new Resume(uuid, rs.getString("full_name")));
            }

            sqlHelper.blockExecute("SELECT * FROM contact" +
                                        "   JOIN section ON contact.resume_uuid = section.resume_uuid" +
                                        "  WHERE contact.resume_uuid = ?", psContact -> {
                for (var r : map.values()) {
                    psContact.setString(1, r.getUuid());
                    var rsContact = psContact.executeQuery();
                    while (rsContact.next()) {
                        addContacts(r, rsContact);
                        addSections(r, rsContact);
                    }
                }
                return null;
            });

            // Либо так?
//            sqlHelper.blockExecute("SELECT * FROM section WHERE resume_uuid = ?", psSection -> {
//                for (var r : map.values()) {
//                    psSection.setString(1, r.getUuid());
//                    var rsSection = psSection.executeQuery();
//                    while (rsSection.next()) {
//                        addSections(r, rsSection);
//                    }
//                }
//                return null;
//            });
            return new ArrayList<>(map.values());
        });
    }


    @Override
    public int size() {
        return sqlHelper.blockExecute("SELECT COUNT(*) FROM resume", ps -> {
            var rs = ps.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }

    private static void insertContacts(Resume r, Connection conn) throws SQLException {

        try (var ps = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?, ?, ?)")) {
            for (var el : r.getContacts().entrySet()) {
                ps.setString(1, r.getUuid());
                ps.setString(2, el.getKey().name());
                ps.setString(3, el.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private static void insertSections(Resume r, Connection conn) throws SQLException {
        try (var ps = conn.prepareStatement(
                "INSERT INTO section (resume_uuid, section_type, section_value) VALUES (?,?,?)")) {
            for (var entry : r.getSections().entrySet()) {
                var type = entry.getKey();
                var section = entry.getValue();

                ps.setString(1, r.getUuid());
                ps.setString(2, type.name());

                switch (type) {
                    case PERSONAL:
                    case OBJECTIVE:
                        ps.setString(3, ((TextSection) section).getContent());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        ps.setString(3, String.join("\n", ((ListSection) section).getStrings()));
                        break;
                    default:
                        ps.setString(3, "");
                }

                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private static void addContacts(Resume r, ResultSet rs) throws SQLException {
        var contactType = rs.getString("type");
        if (contactType != null) {
            r.setContacts(ContactType.valueOf(contactType), rs.getString("value"));
        }
    }

    private static void addSections(Resume r, ResultSet rs) throws SQLException {
        var sectionType = rs.getString("section_type");
        if (sectionType != null) {
            var type = SectionType.valueOf(sectionType);
            var sectionValue = rs.getString("section_value");
            var section = switch (type) {
                case PERSONAL, OBJECTIVE -> new TextSection(sectionValue);
                case ACHIEVEMENT, QUALIFICATIONS -> new ListSection(sectionValue.split("\n"));
                default -> null;
            };

            if (section != null) {
                r.setSections(type, section);
            }
        }
    }
}
