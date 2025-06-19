package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {
    @Override
    public final void clear() {
        specialClear();
    }

    @Override
    public final void update(Resume resume) {
        int index = getSearchKey(resume.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        }
        specialUpdate(resume, index);
    }

    @Override
    public final void delete(String uuid) {
        int resumeIndex = getSearchKey(uuid);
        if (resumeIndex < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            specialDelete(resumeIndex);
        }
    }

    @Override
    public final void save(Resume resume) {
        if (!getClass().equals(ListStorage.class)) {
            if (specialSize() >= AbstractArrayStorage.STORAGE_LIMIT) {
                throw new StorageException("Storage overflow", resume.getUuid());
            }
        }

        int resumeIndex = getSearchKey(resume.getUuid());
        if (resumeIndex >= 0) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            specialInsert(resume, resumeIndex);
        }
    }

    @Override
    public final Resume get(String uuid) {
        int index = getSearchKey(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }

        return specialGet(index);
    }

    @Override
    public final Resume[] getAll() {
        return specialGetAll();
    }

    @Override
    public final int size() {
        return specialSize();
    }

    protected abstract void specialClear();

    protected abstract void specialInsert(Resume resume, int index);

    protected abstract void specialUpdate(Resume resume, int index);

    protected abstract Resume specialGet(int index);

    protected abstract Resume[] specialGetAll();

    protected abstract int specialSize();

    protected abstract void specialDelete(int resumeIndex);

    protected abstract int getSearchKey(String uuid);
}
