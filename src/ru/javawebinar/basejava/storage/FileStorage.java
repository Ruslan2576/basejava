package ru.javawebinar.basejava.storage;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.strategy.SerializerStrategy;

public class FileStorage extends AbstractStorage<File> {
    private final SerializerStrategy serializerStrategy;
    private final File directory;

    public FileStorage(File directory, SerializerStrategy serializerStrategy) {
        Objects.requireNonNull(directory, "directory mustn't be null");
        this.serializerStrategy = serializerStrategy;
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + "isn't directory");
        }

        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + "isn't readable/writable");
        }

        this.directory = directory;
    }

    @Override
    protected void doClear() {
        File[] files = createAndCheckDir(directory, "IO error");
        for (File file : files) {
            doDelete(file);
        }
    }

    @Override
    protected void doInsert(Resume resume, File file) {
        try {
            if (file.createNewFile()) {
                doUpdate(resume, file);
            }
        } catch (IOException e) {
            throw new StorageException("Couldn't create file " + file.getAbsolutePath(), file.getName(), e);
        }
    }

    @Override
    protected void doUpdate(Resume resume, File file) {
        try {
            serializerStrategy.doWrite(resume, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File write error", resume.getUuid(), e);
        }
    }

    @Override
    protected Resume doGet(File file) {
        try {
            return serializerStrategy.doRead(new BufferedInputStream((new FileInputStream(file))));
        } catch (IOException e) {
            throw new StorageException("File read error", file.getName(), e);
        }
    }

    @Override
    protected List<Resume> doGetAll() {
        File[] files = createAndCheckDir(directory, "Directory read error");
        List<Resume> list = new ArrayList<>();
        for (File file : files) {
            list.add(doGet(file));
        }

        return list;
    }

    @Override
    protected int doSize() {
        return createAndCheckDir(directory, "Directory read error").length;
    }

    @Override
    protected void doDelete(File file) {
        if (!file.delete()) {
            throw new StorageException("File delete error");
        }
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    private static File[] createAndCheckDir(File directory, String message) {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException(message);
        }
        return files;
    }
}
