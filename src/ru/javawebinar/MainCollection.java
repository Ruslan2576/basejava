package ru.javawebinar;

import java.util.Arrays;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.ListStorage;
import ru.javawebinar.basejava.storage.Storage;

public class MainCollection {
    public static void main(String[] args) {
        Storage storage = new ListStorage();
        storage.save(new Resume("uuid1"));
        storage.save(new Resume("uuid2"));
        storage.save(new Resume("uuid3"));

        storage.delete("uuid2");
        storage.update(new Resume("uuid3"));
        System.out.println(Arrays.toString(storage.getAll()));
    }
}
