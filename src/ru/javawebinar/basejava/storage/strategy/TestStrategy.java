package ru.javawebinar.basejava.storage.strategy;

import java.io.File;
import java.nio.file.Path;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.ResumeTestData;

public class TestStrategy {
    public static void main(String[] args) {
        String dir = "C:\\Java\\basejava\\storage";
        PathStorage context1 = new PathStorage(dir, new ObjectStreamStrategy());
        context1.doInsert(ResumeTestData.createResume("uuid1", "Name1"), Path.of(dir + "\\test.bin"));
        Resume resume1 = context1.doGet(Path.of(dir + "\\test.bin"));
        System.out.println(resume1);

        FileStorage context2 = new FileStorage(new File(dir), new ObjectStreamStorage());
        context2.doInsert(ResumeTestData.createResume("uuid2", "Name2"), new File(dir + "\\newTest.bin"));
        Resume resume2 = context2.doGet(new File(dir + "\\newTest.bin"));
        System.out.println(resume2);
    }
}

