package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.io.Serial;
import java.io.Serializable;

/**
 * Array based storage for Resumes.
 */

public class ArrayStorage extends AbstractArrayStorage {
    @Override
    protected void specialInsert(Resume resume, int index) {
        storage[storageSize] = resume;
    }

    @Override
    protected void specialDelete(int resumeIndex) {
        storage[resumeIndex] = storage[storageSize - 1];
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
