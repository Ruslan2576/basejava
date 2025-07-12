package ru.javawebinar.basejava.storage;

import java.util.ArrayList;
import java.util.List;
import ru.javawebinar.basejava.model.Resume;

public class ListStorage extends AbstractStorage<Integer> {
    protected final List<Resume> list = new ArrayList<>();

    public Integer getSearchKey(String searchKey) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(searchKey)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void doClear() {
        list.clear();
    }

    @Override
    public void doUpdate(Resume r, Integer searchKey) {
        list.set(searchKey, r);
    }

    @Override
    public void doInsert(Resume r, Integer searchKey) {
        list.add(r);
    }

    @Override
    public Resume doGet(Integer searchKey) {
        return list.get(searchKey);
    }

    @Override
    protected void doDelete(Integer searchKey) {
        list.remove(searchKey.intValue());
    }

    @Override
    public List<Resume> doGetAll() {
        return new ArrayList<>(list);
    }

    @Override
    public int doSize() {
        return list.size();
    }

    protected boolean isExist(Integer searchKey) {
        return searchKey >= 0;
    }
}