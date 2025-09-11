package ru.javawebinar.basejava.sql;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;

import java.sql.*;

public class SqlHelper {
    private final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public <T> T blockExecute(String query, ABlockOfCode<T> blockOfCode) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            return blockOfCode.myExecute(ps);
        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) {
                throw new ExistStorageException(e);
            }
            throw new StorageException(e);
        }
    }
}
