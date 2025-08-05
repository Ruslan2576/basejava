package ru.javawebinar.basejava.storage.strategy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import ru.javawebinar.basejava.model.Resume;

public interface Strategy {
    Resume doRead(InputStream inputStream) throws IOException;

    void doWrite(Resume resume, OutputStream outputStream) throws IOException;
}
