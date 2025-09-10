package ru.javawebinar.basejava.sql;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.Storage;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SqlStorage implements Storage {
    public static final String UPDATE = "UPDATE resume SET uuid = ?, full_name = ? WHERE uuid = ?;";
    public static final String INSERT = "INSERT INTO resume (uuid, full_name) VALUES (?, ?)";
    public static final String DELETE_ALL_RESUME = "DELETE FROM resume";
    public static final String SELECT = "SELECT * FROM resume r WHERE r.uuid = ?";
    public static final String DELETE_FROM_RESUME = "DELETE FROM resume WHERE uuid = ?";
    public static final String SELECT_ALL_RESUME = "SELECT * FROM resume";
    private final SqlHelper sqlHelper = new SqlHelper();
    public final ConnectionFactory connectionFactory;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void clear() {
        sqlHelper.blockExecute(PreparedStatement::execute, DELETE_ALL_RESUME, connectionFactory);
    }

    @Override
    public void update(Resume r) {
        sqlHelper.blockExecute(ps -> {
            ps.setString(1, r.getUuid());
            ps.setString(2, r.getFullName());
            ps.setString(3, r.getUuid());

            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(r.getUuid());
            }
        }, UPDATE, connectionFactory);
    }

    @Override
    public void save(Resume r) {
        sqlHelper.blockExecute(ps -> {
            ps.setString(1, r.getUuid());
            ps.setString(2, r.getFullName());

            try {
                ps.execute();
            } catch (Exception e) {
                throw new ExistStorageException(r.getUuid());
            }
        }, INSERT, connectionFactory);
    }

    @Override
    public Resume get(String uuid) {
        StringBuilder fullName = new StringBuilder();
        sqlHelper.blockExecute(ps -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            fullName.append(rs.getString("full_name"));
        }, SELECT, connectionFactory);
        return new Resume(uuid, fullName.toString());

    }


    @Override
    public void delete(String uuid) {
        sqlHelper.blockExecute(ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
        }, DELETE_FROM_RESUME, connectionFactory);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> resumes = new ArrayList<>();
        sqlHelper.blockExecute(ps -> {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resumes.add(new Resume(rs.getString("uuid").trim(), rs.getString("full_name")));
            }
            resumes.sort(Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid));
        }, SELECT_ALL_RESUME, connectionFactory);
        return resumes;
    }

    @Override
    public int size() {
        return getAllSorted().size();
    }
}
