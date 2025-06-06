package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes.
 */

public class ArrayStorage extends AbstractArrayStorage {
    public void save(Resume resume) {
        int resumeIndex = templateMethod(resume.getUuid());
        if (resumeIndex >= 0) {
            System.out.printf("Ошибка: %s уже есть в хранилище%n", resume.getUuid());
            return;
        }

        if (storageSize < storage.length) {
            storage[storageSize] = resume;
            ++storageSize;
        } else {
            System.out.println("Ошибка: хранилище переполнено");
        }
    }

    protected int getSearchKey(String uuid) {
        for (int i = 0; i < storageSize; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
