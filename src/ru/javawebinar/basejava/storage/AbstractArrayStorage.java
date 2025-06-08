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

    public final void update(Resume resume) {
        int resumeIndex = getSearchKey(resume.getUuid());
        if (resumeIndex >= 0) {
            storage[resumeIndex] = resume;
            return;
        }
        System.out.printf("Ошибка: не могу обновить %s его нет в хранилище%n", resume.getUuid());
    }

    public final void save(Resume resume) {
        if (storageSize >= storage.length) {
            System.out.println("Ошибка: хранилище переполнено");
            return;
        }

        int resumeIndex = getSearchKey(resume.getUuid());
        if (resumeIndex >= 0) {
            System.out.printf("Ошибка: %s уже есть в хранилище%n", resume.getUuid());
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
        System.out.printf("Ошибка: нет такого %s резюме%n", uuid);
        return null;
    }

    public final void delete(String uuid) {
        int resumeIndex = getSearchKey(uuid);
        if (resumeIndex < 0) {
            System.out.printf("Ошибка: не могу удалить %s его нет в хранилище%n", uuid);
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
