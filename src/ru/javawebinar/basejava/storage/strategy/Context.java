package ru.javawebinar.basejava.storage.strategy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import ru.javawebinar.basejava.model.Resume;

public class Context {
    private final Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public Resume read(String fileName) throws IOException {
        return strategy.doRead(new BufferedInputStream(new FileInputStream(fileName)));
    }

    public void write(Resume resume, String fileName) throws IOException {
        strategy.doWrite(resume, new BufferedOutputStream(new FileOutputStream(fileName)));
    }
}
