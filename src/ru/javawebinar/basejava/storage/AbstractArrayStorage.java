package ru.javawebinar.basejava.storage;

import java.util.Arrays;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int storageSize;

    public void clear() {
        Arrays.fill(storage, 0, storageSize, null);
        storageSize = 0;
    }

    public final void update(Resume resume) {
        int resumeIndex = getSearchKey(resume.getUuid());
        if (resumeIndex < 0) {
            throw new NotExistStorageException(resume.getUuid());
        }

        storage[resumeIndex] = resume;
    }

    public final void save(Resume resume) {
        if (storageSize >= storage.length) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }

        int resumeIndex = getSearchKey(resume.getUuid());
        if (resumeIndex >= 0) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            specialInsert(resume, resumeIndex);
            ++storageSize;
        }
    }

    public final Resume get(String uuid) {
        int resumeIndex = getSearchKey(uuid);
        if (resumeIndex >= 0) {
            return storage[resumeIndex];
        }
        throw new NotExistStorageException(uuid);
    }

    public final void delete(String uuid) {
        int resumeIndex = getSearchKey(uuid);
        if (resumeIndex < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            specialDelete(resumeIndex);
            storage[storageSize - 1] = null;
            --storageSize;
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, storageSize);
    }

    public int size() {
        return storageSize;
    }

    protected abstract void specialInsert(Resume resume, int index);

    protected abstract void specialDelete(int resumeIndex);

    protected abstract int getSearchKey(String uuid);
}
