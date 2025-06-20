package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes.
 */

public class ArrayStorage extends AbstractArrayStorage {
    @Override
    protected void doInsert(Resume resume, int index) {
        storage[storageSize] = resume;
        ++storageSize;
    }

    @Override
    protected void doDelete(int resumeIndex) {
        storage[resumeIndex] = storage[storageSize - 1];
        storage[storageSize - 1] = null;
        --storageSize;
    }

    @Override
    protected int getSearchKey(String uuid) {
        for (int i = 0; i < storageSize; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
