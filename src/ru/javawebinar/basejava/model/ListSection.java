package ru.javawebinar.basejava.model;

import java.io.Serial;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ListSection extends Section {
    @Serial
    private static final long serialVersionUID = 1L;
    private List<String> strings;

    public ListSection() {
    }

    public ListSection(String ... items) {
        this(Arrays.asList(items));
    }

    public ListSection(List<String> list) {
        Objects.requireNonNull(list, "list mustn't be null");
        this.strings = list;
    }

    public List<String> getStrings() {
        return strings;
    }

    @Override
    public String toString() {
        return strings.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ListSection that = (ListSection) object;
        return Objects.equals(strings, that.strings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(strings);
    }

    public List<String> getItems() {
        return strings;
    }
}
