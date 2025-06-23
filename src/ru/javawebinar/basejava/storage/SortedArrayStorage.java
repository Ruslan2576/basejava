package ru.javawebinar.basejava.storage;

import java.util.Arrays;
import ru.javawebinar.basejava.model.Resume;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected void doInsert(Resume resume, Object searchKey) {
        checkOverflow(resume);
        int insertPoint = -(int) searchKey - 1;
        System.arraycopy(storage, insertPoint, storage, insertPoint + 1, storageSize - insertPoint);
        storage[insertPoint] = resume;
        ++storageSize;
    }

    @Override
    protected void doDelete(Object searchKey) {
        System.arraycopy(storage, (int) searchKey + 1, storage, (int) searchKey, storageSize - (int) searchKey - 1);
        --storageSize;
    }

    @Override
    protected Object getSearchKey(Object searchKey) {
        Resume resume = new Resume((String) searchKey);
        return Arrays.binarySearch(storage, 0, storageSize, resume);
    }
}
