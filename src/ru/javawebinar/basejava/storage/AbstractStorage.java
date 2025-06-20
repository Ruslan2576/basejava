package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {
    @Override
    public final void clear() {
        doClear();
    }

    @Override
    public final void update(Resume resume) {
        Object searchKey = getNotExistingSearchKey(resume.getUuid());
        doUpdate(resume, (int) searchKey);
    }

    @Override
    public final void delete(String uuid) {
        Object searchKey = getNotExistingSearchKey(uuid);
        doDelete((int) searchKey);
    }

    @Override
    public final void save(Resume resume) {
        if (!getClass().equals(ListStorage.class)) {
            if (doSize() >= AbstractArrayStorage.STORAGE_LIMIT) {
                throw new StorageException("Storage overflow", resume.getUuid());
            }
        }

        Object searchKey = getExistingSearchKey(resume.getUuid());
        doInsert(resume, (int) searchKey);
    }

    @Override
    public final Resume get(String uuid) {
        Object searchKey = getNotExistingSearchKey(uuid);
        return doGet((int) searchKey);
    }

    @Override
    public final Resume[] getAll() {
        return doGetAll();
    }

    @Override
    public final int size() {
        return doSize();
    }

    private Object getExistingSearchKey(String uuid) {
        Object searchKey = isExist(uuid);
        if ((int) searchKey >= 0) {
            throw new ExistStorageException(uuid);
        }

        return searchKey;
    }

    private Object getNotExistingSearchKey(String uuid) {
        Object searchKey = isExist(uuid);
        if ((int) searchKey < 0) {
            throw new NotExistStorageException(uuid);
        }

        return searchKey;
    }

    protected abstract void doClear();

    protected abstract void doInsert(Resume resume, int index);

    protected abstract void doUpdate(Resume resume, int index);

    protected abstract Resume doGet(int index);

    protected abstract Resume[] doGetAll();

    protected abstract int doSize();

    protected abstract void doDelete(int resumeIndex);

    protected abstract int getSearchKey(String uuid);

    protected abstract int isExist(String uuid);
}
