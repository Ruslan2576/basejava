package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes.
 */

public class ArrayStorage extends AbstractArrayStorage {
    @Override
    protected void doInsert(Resume resume, Integer searchKey) {
        checkOverflow(resume);
        storage[storageSize] = resume;
        storageSize++;
    }

    @Override
    protected void doDelete(Integer searchKey) {
        storage[searchKey] = storage[storageSize - 1];
        storage[storageSize - 1] = null;
        storageSize--;
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < storageSize; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
