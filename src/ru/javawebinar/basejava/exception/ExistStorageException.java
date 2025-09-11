package ru.javawebinar.basejava.exception;

import java.sql.SQLException;

public class ExistStorageException extends StorageException {
    public ExistStorageException(String uuid) {
        super("Resume " + uuid + " already exist", uuid);
    }

    public ExistStorageException(Exception e) {
        this(e.getMessage());
    }
}
