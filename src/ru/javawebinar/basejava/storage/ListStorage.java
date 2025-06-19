package ru.javawebinar.basejava.storage;

import java.util.ArrayList;
import ru.javawebinar.basejava.model.Resume;

public class ListStorage extends AbstractStorage {
    protected final ArrayList<Resume> list = new ArrayList<>();

    @Override
    public void specialClear() {
        list.clear();
    }

    @Override
    public void specialUpdate(Resume r, int index) {
        list.set(index, r);
    }

    @Override
    public void specialInsert(Resume r, int index) {
        list.add(r);
    }

    @Override
    public Resume specialGet(int index) {
        return list.get(index);
    }

    @Override
    protected void specialDelete(int index) {
        list.remove(index);
    }

    @Override
    public Resume[] specialGetAll() {
        Resume[] resumes = new Resume[list.size()];
        for (int i = 0; i < resumes.length; i++) {
            resumes[i] = list.get(i);
        }
        return resumes;
    }

    @Override
    public int specialSize() {
        return list.size();
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