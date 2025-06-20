package ru.javawebinar.basejava.storage;

import java.util.Arrays;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int storageSize;

    @Override
    public void doClear() {
        Arrays.fill(storage, 0, storageSize, null);
        storageSize = 0;
    }

    @Override
    protected void doUpdate(Resume resume, int index) {
        storage[index] = resume;
    }

    @Override
    protected Resume doGet(int index) {
        return storage[index];
    }

    public Resume[] doGetAll() {
        return Arrays.copyOf(storage, storageSize);
    }

    public int doSize() {
        return storageSize;
    }

    @Override
    protected int isExist(String uuid) {
        return getSearchKey(uuid);
    }
}
