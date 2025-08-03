package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.io.IOException;

public class TestStrategy {
    public static void main(String[] args) throws IOException {
        String dir = "C:\\Java\\basejava\\storage";
        Context context1 = new Context(new ObjectStreamPathStorage(dir));
        context1.write(ResumeTestData.createResume("uuid1", "Name1"), dir + "\\test.bin");
        Resume resume1 = context1.read(dir + "\\test.bin");
        System.out.println(resume1);

        Context context2 = new Context(new ObjectStreamStorage(new File(dir)));
        context1.write(ResumeTestData.createResume("uuid2", "Name2"), dir + "\\newTest.bin");
        Resume resume2 = context2.read(dir + "\\newTest.bin");
        System.out.println(resume2);
    }
}

