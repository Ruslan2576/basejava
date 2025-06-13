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
        assertEquals(0, storage.size());
    }

    @Test
    void update() {
        Resume resumeTest = storage.getAll()[0];
        storage.update(resumeTest);
        assertEquals(resumeTest, storage.getAll()[0]);
        assertEquals(resumeTest.getUuid(), storage.getAll()[0].getUuid());
    }

    @Test
    void updateNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.update(new Resume("uuid100")));
    }

    @Test
    void save() {
        storage.save(new Resume());
        assertEquals(4, storage.size());
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
        assertEquals(storage.getAll()[0], storage.get("uuid1"));
    }

    @Test()
    void getNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.get("dummy"));
    }

    @Test
    void delete() {
        storage.delete("uuid1");
        assertEquals(2, storage.size());
    }

    @Test
    void deleteNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.delete("dummy"));
    }


    @Test
    void getAll() {
        assertEquals(3, storage.getAll().length);
    }

    @Test
    void size() {
        assertEquals(3, storage.size());
    }
}