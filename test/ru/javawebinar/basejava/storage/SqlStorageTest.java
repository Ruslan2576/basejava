package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.sql.SqlStorage;

public class SqlStorageTest extends AbstractStorageTest {
    public SqlStorageTest() {
        super(new SqlStorage("jdbc:postgresql://localhost:5432/resumes", "postgres", "postgres"));
    }

    @Override
    void saveOverflow() {
    }
}
