package ru.javawebinar;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.MapResumeStorage;
import ru.javawebinar.basejava.storage.MapUuidStorage;
import ru.javawebinar.basejava.storage.Storage;

public class MainCollection {
    public static void main(String[] args) {
        Storage storage = new MapResumeStorage();
        storage.save(new Resume("uuid1", "Ira"));
        storage.save(new Resume("uuid2", "Anna"));
        storage.save(new Resume("uuid3", "John"));
        storage.save(new Resume("uuid4", "Arthur"));

        for (Resume r : storage.getAllSorted()) {
            System.out.println(r.getFullName());
        }
        System.out.println(storage.get("uuid1"));
    }
}
