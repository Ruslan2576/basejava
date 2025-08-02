package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import java.io.*;

public class ObjectStreamPathStorage extends AbstractPathStorage {
    protected ObjectStreamPathStorage(String dir) {
        super(dir);
    }

    @Override
    protected void doWrite(Resume resume, OutputStream path) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(path)) {
            oos.writeObject(resume);
        }
    }

    @Override
    protected Resume doRead(InputStream path) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(path)) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Error read resume", null, e);
        }
    }
}
