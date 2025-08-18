package ru.javawebinar.basejava.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CompanySection extends Section {
    private static final long serialVersionUID = 1L;
    private List<Company> list;

    public CompanySection() {
    }

    public CompanySection(Company ... items) {
        this(Arrays.asList(items));
    }

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

    public List<Company> getCompanies() {
        return list;
    }
}
