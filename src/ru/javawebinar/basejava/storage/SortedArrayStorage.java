package ru.javawebinar.basejava.storage;

import java.util.Arrays;
import ru.javawebinar.basejava.model.Resume;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    void mySort(Resume resume, int index) {
        int insertionPointer = -index - 1;
        System.arraycopy(storage, insertionPointer, storage, insertionPointer + 1, storageSize - insertionPointer);
        storage[insertionPointer] = resume;
        ++storageSize;
    }

    protected int getSearchKey(String uuid) {
        Resume resume = new Resume();
        resume.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, storageSize, resume);
    }
}
