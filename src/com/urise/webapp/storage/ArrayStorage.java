package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.Arrays;

/**
 * Array based storage for Resumes.
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int storageSize;

    public void clear() {
        Arrays.fill(storage, 0, storageSize, null);
        storageSize = 0;
    }

    public void update(Resume resume) {
        for (int i = 0; i < storageSize; i++) {
            if (resume.getUuid().equals(storage[i].getUuid())) {
                resume.setUuid("abrakadabra");
                return;
            }
        }
        System.out.printf("Ошибка: не могу обновить %s его нет в хранилище%n", resume.getUuid());
    }

    public void save(Resume r) {
        for (int i = 0; i < storageSize; i++) {
            if (r.getUuid().equals(storage[i].getUuid())) {
                System.out.printf("Ошибка: %s уже есть в хранилище%n", r.getUuid());
                return;
            }
        }

        if (storageSize < storage.length) {
            storage[storageSize++] = r;
        } else {
            System.out.println("Ошибка: хранилище переполнено");
        }
    }

    public Resume get(String uuid) {
        for (int i = 0; i < storageSize; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return storage[i];
            }
        }
        System.out.printf("Ошибка: нет такого %s резюме%n", uuid);
        return null;
    }

    public void delete(String uuid) {
        for (int i = 0; i < storageSize; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                System.arraycopy(storage, i + 1, storage, i, storageSize - i - 1);
                storage[--storageSize] = null;
                return;
            }
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
}
