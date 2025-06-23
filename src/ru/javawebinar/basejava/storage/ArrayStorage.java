package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes.
 */

public class ArrayStorage extends AbstractArrayStorage {
    @Override
    protected void doInsert(Resume resume, Object searchKey) {
        checkOverflow(resume);
        storage[storageSize] = resume;
        ++storageSize;
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage[(int) searchKey] = storage[storageSize - 1];
        storage[storageSize - 1] = null;
        --storageSize;
    }

    @Override
    protected Object getSearchKey(Object searchKey) {
        for (int i = 0; i < storageSize; i++) {
            if (searchKey.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
