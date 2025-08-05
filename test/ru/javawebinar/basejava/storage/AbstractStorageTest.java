package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.javawebinar.basejava.storage.AbstractArrayStorage.STORAGE_LIMIT;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = new File("C:\\Java\\basejava\\storage");
    protected final Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String SPECIAL_UUID = "specialUuid777";
    private static final Resume RESUME_1 = ResumeTestData.createResume(UUID_1, "Name1");
    private static final Resume RESUME_2 = ResumeTestData.createResume(UUID_2, "Name2");
    private static final Resume RESUME_3 = ResumeTestData.createResume(UUID_3, "Name3");
    private static final Resume SPECAL_RESUME = ResumeTestData.createResume(SPECIAL_UUID, "Name4");
    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    void clear() {
        storage.clear();
        assertSize(0);
        assertEquals(new ArrayList<>(), storage.getAllSorted());
    }

    @Test
    void update() {
        Resume resumeTest = ResumeTestData.createResume(UUID_1, "New Name");
        storage.update(resumeTest);
        assertEquals(resumeTest, storage.get(UUID_1));
    }

    @Test
    void updateNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.update(SPECAL_RESUME));
    }

    @Test
    void save() {
        storage.save(SPECAL_RESUME);
        assertGet(SPECAL_RESUME);
        assertSize(storage.size());
    }

    @Test
    void saveExist() {
        assertThrows(ExistStorageException.class, () -> storage.save(storage.getAllSorted().getFirst()));
    }

    @Test
    void saveOverflow() {
        storage.clear();
        try {
            for (int i = 0; i < STORAGE_LIMIT; i++) {
                storage.save(ResumeTestData.createResume("uuid" + i + 1, "name" + i + 1));
            }
        } catch (StorageException e) {
            fail("переполнение произошло раньше времени");
        }
        assertThrows(StorageException.class, () -> storage.save(ResumeTestData.createResume("uuid10001", "name10001")));
    }

    @Test
    void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }

    @Test()
    void getNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.get("dummy"));
    }

    @Test
    void delete() {
        storage.delete("uuid1");
        assertSize(2);
        assertThrows(NotExistStorageException.class, () -> storage.get("uuid1"));
    }

    @Test
    void deleteNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.delete("dummy"));
    }

    @Test
    void getAll() {
        final List<Resume> expected = List.of(RESUME_1, RESUME_2, RESUME_3);
        assertEquals(expected, storage.getAllSorted());
        assertEquals(expected.size(), storage.size());
    }

    @Test
    void size() {
        assertSize(storage.size());
    }

    void assertSize(int size) {
        assertEquals(size, storage.size());
    }
}