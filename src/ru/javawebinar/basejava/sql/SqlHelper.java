package ru.javawebinar.basejava.sql;

import ru.javawebinar.basejava.exception.StorageException;
import java.sql.*;

public class SqlHelper {
    public void blockExecute(ABlockOfCode aBlockOfCode, String query, ConnectionFactory connectionFactory) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            aBlockOfCode.execute(ps);
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
}
