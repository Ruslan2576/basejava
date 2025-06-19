package ru.javawebinar.basejava.storage;

import java.util.Arrays;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int storageSize;

    @Override
    public void specialClear() {
        Arrays.fill(storage, 0, storageSize, null);
        storageSize = 0;
    }

    @Override
    protected void specialUpdate(Resume resume, int index) {
        storage[index] = resume;
    }

    @Override
    protected Resume specialGet(int index) {
        return storage[index];
    }

    public Resume[] specialGetAll() {
        return Arrays.copyOf(storage, storageSize);
    }

    public int specialSize() {
        return storageSize;
    }
}
