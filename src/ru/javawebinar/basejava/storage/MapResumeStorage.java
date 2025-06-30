package ru.javawebinar.basejava.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.javawebinar.basejava.model.Resume;

public class MapResumeStorage extends AbstractStorage {
    protected final Map<String, Resume> map = new HashMap<>();

    @Override
    protected void doClear() {
        map.clear();
    }

    @Override
    protected void doInsert(Resume resume, Object searchKey) {
        map.put((String) searchKey, resume);
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        map.put(((Resume) searchKey).getUuid(), resume);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return map.get(((Resume) searchKey).getUuid());
    }

    @Override
    protected List<Resume> doGetAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    protected int doSize() {
        return map.size();
    }

    @Override
    protected void doDelete(Object searchKey) {
        map.remove(((Resume) searchKey).getUuid());
    }

    @Override
    protected Object getSearchKey(Object searchKey) {
        Resume resume = map.get((String) searchKey);
        if (resume == null) {
            return searchKey;
        }
        return resume;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        if (searchKey instanceof Resume resume) {
            return map.containsKey(resume.getUuid());
        }
        return false;
    }
}
