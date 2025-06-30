package ru.javawebinar.basejava.storage;

import java.util.Arrays;
import java.util.Comparator;
import ru.javawebinar.basejava.model.Resume;

public class SortedArrayStorage extends AbstractArrayStorage {
    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getUuid);

    @Override
    protected void doInsert(Resume resume, Object searchKey) {
        checkOverflow(resume);
        int insertPoint = -(int) searchKey - 1;
        System.arraycopy(storage, insertPoint, storage, insertPoint + 1, storageSize - insertPoint);
        storage[insertPoint] = resume;
        storageSize++;
    }

    @Override
    protected void doDelete(Object searchKey) {
        int index = (int) searchKey;
        System.arraycopy(storage, index + 1, storage, index, storageSize - index - 1);
        storageSize--;
    }

    @Override
    protected Object getSearchKey(Object searchKey) {
        Resume resume = new Resume((String) searchKey, "Choco");
        return Arrays.binarySearch(storage, 0, storageSize, resume, RESUME_COMPARATOR);
    }
}
