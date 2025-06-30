package ru.javawebinar.basejava.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.javawebinar.basejava.model.Resume;

public class MapUuidStorage extends AbstractStorage {
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
        map.put((String) searchKey, resume);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return map.get((String) searchKey);
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
        map.remove((String) searchKey);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return map.containsKey((String) searchKey);
    }

    @Override
    protected Object getSearchKey(Object searchKey) {
        return searchKey;
    }
}
