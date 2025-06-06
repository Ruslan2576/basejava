package ru.javawebinar.basejava.storage;

import java.util.Arrays;
import ru.javawebinar.basejava.model.Resume;

public class SortedArrayStorage extends AbstractArrayStorage {
    public void save(Resume resume) {
        int resumeIndex = templateMethod(resume.getUuid());
        if (resumeIndex >= 0) {
            System.out.printf("Ошибка: %s уже есть в хранилище%n", resume.getUuid());
            return;
        }

        if (storageSize >= STORAGE_LIMIT) {
            System.out.println("Ошибка: хранилище переполнено");
            return;
        }

        int insertPoint = -resumeIndex - 1;
        System.arraycopy(storage, insertPoint, storage, insertPoint + 1, storageSize - insertPoint);
        storage[insertPoint] = resume;
        ++storageSize;
    }

    protected int getSearchKey(String uuid) {
        Resume resume = new Resume();
        resume.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, storageSize, resume);
    }
}
