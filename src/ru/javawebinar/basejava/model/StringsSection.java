package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class StringsSection extends Section {
    private final List<String> strings;

    public StringsSection(List<String> list) {
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
        StringsSection that = (StringsSection) object;
        return Objects.equals(strings, that.strings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(strings);
    }
}
