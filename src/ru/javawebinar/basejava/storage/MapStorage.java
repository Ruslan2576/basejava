package ru.javawebinar.basejava.storage;

import java.util.HashMap;
import java.util.Map;
import ru.javawebinar.basejava.model.Resume;

public class MapStorage extends AbstractStorage {
    Map<Integer, Resume> map = new HashMap<>();

    @Override
    protected void doClear() {
        map.clear();
    }

    @Override
    protected void doInsert(Resume resume, int key) {
        map.put(size() + 1, resume);
    }

    @Override
    protected void doUpdate(Resume resume, int key) {
        map.put(key, resume);
    }

    @Override
    protected Resume doGet(int key) {
        return map.get(key);
    }

    @Override
    protected Resume[] doGetAll() {
        return map.values().toArray(new Resume[0]);
    }

    @Override
    protected int doSize() {
        return map.size();
    }

    @Override
    protected void doDelete(int key) {
        map.remove(key);
    }

    @Override
    protected int isExist(String uuid) {
        return getSearchKey(uuid);
    }

    @Override
    protected int getSearchKey(String uuid) {
        for (Integer key : map.keySet()) {
            if (map.get(key).getUuid().equals(uuid)) {
                return key;
            }
        }

        return -1;
    }
}
