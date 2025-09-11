package ru.javawebinar.basejava.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface ABlockOfCode<T> {
    T myExecute(PreparedStatement ps) throws SQLException;
}
