package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.Arrays;

/**
 * Array based storage for Resumes.
 */
public class ArrayStorage {
    public static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int storageSize;

    public void clear() {
        Arrays.fill(storage, 0, storageSize, null);
        storageSize = 0;
    }

    public void update(Resume resume) {
        if (getSearchKey(resume.getUuid()) >= 0) {
            resume.setUuid("abrakadabra");
            return;
        }
        System.out.printf("Ошибка: не могу обновить %s его нет в хранилище%n", resume.getUuid());
    }

    public void save(Resume resume) {
        if (getSearchKey(resume.getUuid()) >= 0) {
            System.out.printf("Ошибка: %s уже есть в хранилище%n", resume.getUuid());
            return;
        }

        if (storageSize < storage.length) {
            storage[storageSize++] = resume;
        } else {
            System.out.println("Ошибка: хранилище переполнено");
        }
    }

    public Resume get(String uuid) {
        int resumeIndex = getSearchKey(uuid);
        if (getSearchKey(uuid) >= 0) {
            return storage[resumeIndex];
        }
        System.out.printf("Ошибка: нет такого %s резюме%n", uuid);
        return null;
    }

    public void delete(String uuid) {
        int resumeIndex = getSearchKey(uuid);
        if (resumeIndex >= 0) {
            System.arraycopy(storage, resumeIndex + 1, storage, resumeIndex, storageSize - resumeIndex - 1);
            storage[--storageSize] = null;
            return;
        }
        System.out.printf("Ошибка: не могу удалить %s его нет в хранилище%n", uuid);
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */

    public Resume[] getAll() {
        return Arrays.copyOf(storage, storageSize);
    }

    public int size() {
        return storageSize;
    }

    private int getSearchKey(String uuid) {
        for (int i = 0; i < storageSize; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
