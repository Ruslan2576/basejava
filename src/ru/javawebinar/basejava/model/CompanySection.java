package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class CompanySection extends Section {
    private final List<Company> list;

    public CompanySection(List<Company> list) {
        Objects.requireNonNull(list, "list mustn't be null");
        this.list = list;
    }

    @Override
    public String toString() {
        return list.toString().replace(", ", "");
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        CompanySection that = (CompanySection) object;
        return Objects.equals(list, that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }

    public List<Company> getList() {
        return list;
    }
}
