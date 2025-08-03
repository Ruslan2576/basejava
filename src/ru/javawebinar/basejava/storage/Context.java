package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.io.*;
import java.nio.file.Path;

public class Context {
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public Resume read(String fileName) throws IOException {
        return strategy.doRead(new BufferedInputStream(new FileInputStream(fileName)));
    }

    public void write(Resume resume, String fileName) throws IOException {
        strategy.doWrite(resume, new BufferedOutputStream(new FileOutputStream(fileName)));
    }
}
