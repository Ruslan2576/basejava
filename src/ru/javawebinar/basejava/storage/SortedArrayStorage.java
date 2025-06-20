package ru.javawebinar.basejava.storage;

import java.util.Arrays;
import ru.javawebinar.basejava.model.Resume;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected void doInsert(Resume resume, int index) {
        int insertPoint = -index - 1;
        System.arraycopy(storage, insertPoint, storage, insertPoint + 1, storageSize - insertPoint);
        storage[insertPoint] = resume;
        ++storageSize;
    }

    @Override
    protected void doDelete(int resumeIndex) {
        System.arraycopy(storage, resumeIndex + 1, storage, resumeIndex, storageSize - resumeIndex - 1);
        --storageSize;
    }

    @Override
    protected int getSearchKey(String uuid) {
        Resume resume = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, storageSize, resume);
    }
}
