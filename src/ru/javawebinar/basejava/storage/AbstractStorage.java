package ru.javawebinar.basejava.storage;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage<T> implements Storage {
    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected abstract void doClear();

    protected abstract void doInsert(Resume resume, T searchKey);

    protected abstract void doUpdate(Resume resume, T searchKey);

    protected abstract Resume doGet(T searchKey);

    protected abstract List<Resume> doGetAll();

    protected abstract int doSize();

    protected abstract void doDelete(T searchKey);

    protected abstract T getSearchKey(String searchKey);

    protected abstract boolean isExist(T searchKey);

    @Override
    public final void clear() {
        doClear();
    }

    @Override
    public final void update(Resume resume) {
        LOG.info("Update " + resume);
        T searchKey = getNotExistingSearchKey(resume.getUuid());
        doUpdate(resume, searchKey);
    }

    @Override
    public final void delete(String uuid) {
        LOG.info("Delete " + uuid);
        T searchKey = getNotExistingSearchKey(uuid);
        doDelete(searchKey);
    }

    @Override
    public final void save(Resume resume) {
        LOG.info("Save " + resume);
        T searchKey = getExistingSearchKey(resume.getUuid());
        doInsert(resume, searchKey);
    }

    @Override
    public final Resume get(String uuid) {
        LOG.info("Get " + uuid);
        T searchKey = getNotExistingSearchKey(uuid);
        return doGet(searchKey);
    }

    @Override
    public final List<Resume> getAllSorted() {
        LOG.info("getAllSorted");
        List<Resume> sortedResumes = doGetAll();
        Collections.sort(sortedResumes, Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid));
        // sortedResumes.sort(Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid));
        return sortedResumes;
    }

    @Override
    public final int size() {
        return doSize();
    }

    private T getExistingSearchKey(String uuid) {
        T searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        }

        return searchKey;
    }

    private T getNotExistingSearchKey(String uuid) {
        T searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }

        return searchKey;
    }
}
