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
    public void doUpdate(Resume r, Object searchKey) {
        list.set((int) searchKey, r);
    }

    @Override
    public void doInsert(Resume r, Object searchKey) {
        list.add(r);
    }

    @Override
    public Resume doGet(Object searchKey) {
        return list.get((int) searchKey);
    }

    @Override
    protected void doDelete(Object searchKey) {
        list.remove((int) searchKey);
    }

    @Override
    public List<Resume> doGetAll() {
        return list;
    }

    @Override
    public int doSize() {
        return list.size();
    }

    protected boolean isExist(Object searchKey) {
        return (int) searchKey >= 0;
    }

    public Object getSearchKey(Object searchKey) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(searchKey)) {
                return i;
            }
        }
        return -1;
    }
}