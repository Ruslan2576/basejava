package ru.javawebinar.basejava.storage;

import java.util.ArrayList;
import java.util.List;
import ru.javawebinar.basejava.model.Resume;

public class ListStorage extends AbstractStorage {
    protected final List<Resume> list = new ArrayList<>();

    @Override
    public void doClear() {
        list.clear();
    }

    @Override
    public void doUpdate(Resume r, int index) {
        list.set(index, r);
    }

    @Override
    public void doInsert(Resume r, int index) {
        list.add(r);
    }

    @Override
    public Resume doGet(int index) {
        return list.get(index);
    }

    @Override
    protected void doDelete(int index) {
        list.remove(index);
    }

    @Override
    public Resume[] doGetAll() {
        return list.toArray(new Resume[0]);
    }

    @Override
    public int doSize() {
        return list.size();
    }

    @Override
    protected int isExist(String uuid) {
        return getSearchKey(uuid);
    }

    public int getSearchKey(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}