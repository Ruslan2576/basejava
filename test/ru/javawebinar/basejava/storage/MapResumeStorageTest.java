package ru.javawebinar.basejava.storage;

class MapResumeStorageTest extends AbstractStorageTest {
    public MapResumeStorageTest() {
        super(new MapResumeStorage());
    }

    @Override
    public void saveOverflow() {
    }

}