package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes.
 */

public class ArrayStorage extends AbstractArrayStorage {
    protected int getSearchKey(String uuid) {
        for (int i = 0; i < storageSize; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
