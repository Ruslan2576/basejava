package ru.javawebinar.basejava.storage;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public class ObjectStreamPathStorage extends AbstractPathStorage {
    protected ObjectStreamPathStorage(String dir) {
        super(dir);
    }

    @Override
    public void doWrite(Resume resume, OutputStream path) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(path)) {
            oos.writeObject(resume);
        }
    }

    @Override
    public Resume doRead(InputStream path) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(path)) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Error read resume", null, e);
        }
    }
}
