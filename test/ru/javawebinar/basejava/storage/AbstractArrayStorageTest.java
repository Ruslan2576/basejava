package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.*;
import ru.javawebinar.basejava.exception.*;
import ru.javawebinar.basejava.model.Resume;

import static org.junit.jupiter.api.Assertions.*;
import static ru.javawebinar.basejava.storage.AbstractArrayStorage.STORAGE_LIMIT;

public abstract class AbstractArrayStorageTest {
    protected final Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String SPECIAL_UUID = "specialUuid777";
    private static final Resume SPECAL_RESUME = new Resume(SPECIAL_UUID);
    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    void clear() {
        storage.clear();
        assertSize(0);
        assertArrayEquals(new Resume[]{}, storage.getAll());
    }

    @Test
    void update() {
        Resume resumeTest = new Resume(UUID_1);
        storage.update(resumeTest);
        assertSame(resumeTest, storage.get(UUID_1));
    }

    @Test
    void updateNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.update(new Resume("uuid100")));
    }

    @Test
    void save() {
        storage.save(SPECAL_RESUME);
        assertGet(SPECAL_RESUME);
        assertSize(storage.size());
    }

    @Test
    void saveExist() {
        assertThrows(ExistStorageException.class, () -> storage.save(storage.getAll()[0]));
    }

    @Test
    void saveOverflow() {
        storage.clear();
        try {
            for (int i = 0; i < STORAGE_LIMIT; i++) {
                storage.save(new Resume("uuid" + i + 1));
            }
        } catch (StorageException e) {
            fail("переполнение произошло раньше времени");
        }
        assertThrows(StorageException.class, () -> storage.save(new Resume("uuid10001")));
    }

    @Test
    void get() {
        for (Resume resume : storage.getAll()) {
            assertGet(resume);
        }
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
        final Resume[] expected = {new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3)};
        assertArrayEquals(expected, storage.getAll());
    }

    @Test
    void size() {
        assertSize(storage.size());
    }

    void assertSize(int size) {
        assertEquals(size, storage.size());
    }
}