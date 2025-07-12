package ru.javawebinar.basejava.storage;

import java.util.Arrays;
import java.util.Comparator;
import ru.javawebinar.basejava.model.Resume;

public class SortedArrayStorage extends AbstractArrayStorage {
    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getUuid);

    @Override
    protected void doInsert(Resume resume, Integer searchKey) {
        checkOverflow(resume);
        int insertPoint = -searchKey - 1;
        System.arraycopy(storage, insertPoint, storage, insertPoint + 1, storageSize - insertPoint);
        storage[insertPoint] = resume;
        storageSize++;
    }

    @Override
    protected void doDelete(Integer searchKey) {
        int index = searchKey;
        System.arraycopy(storage, index + 1, storage, index, storageSize - index - 1);
        storageSize--;
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume resume = new Resume(uuid, "Choco");
        return Arrays.binarySearch(storage, 0, storageSize, resume, RESUME_COMPARATOR);
    }
}
