package ru.javawebinar.basejava.storage;

import java.util.Arrays;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int storageSize;

    public void clear() {
        Arrays.fill(storage, 0, storageSize, null);
        storageSize = 0;
    }

    public void update(Resume resume) {
        int resumeIndex = templateMethod(resume.getUuid());
        if (resumeIndex >= 0) {
            Resume newResume = new Resume();
            newResume.setUuid("abracadabra");
            storage[resumeIndex] = newResume;
            return;
        }
        System.out.printf("Ошибка: не могу обновить %s его нет в хранилище%n", resume.getUuid());
    }

    public Resume get(String uuid) {
        int resumeIndex = templateMethod(uuid);
        if (resumeIndex >= 0) {
            return storage[resumeIndex];
        }
        System.out.printf("Ошибка: нет такого %s резюме%n", uuid);
        return null;
    }

    public void delete(String uuid) {
        int resumeIndex = templateMethod(uuid);
        if (resumeIndex >= 0) {
            System.arraycopy(storage, resumeIndex + 1, storage, resumeIndex, storageSize - resumeIndex - 1);
            storage[storageSize - 1] = null;
            --storageSize;
            return;
        }
        System.out.printf("Ошибка: не могу удалить %s его нет в хранилище%n", uuid);
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, storageSize);
    }

    public int size() {
        return storageSize;
    }

    final int templateMethod(String uuid) {
        return getSearchKey(uuid);
    }

    protected abstract int getSearchKey(String uuid);
}
