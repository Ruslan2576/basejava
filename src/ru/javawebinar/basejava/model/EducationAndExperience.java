package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EducationAndExperience extends Section {
    List<DataAboutPerson> data = new ArrayList<>();

    public void addData(DataAboutPerson data) {
        this.data.add(data);
    }

    @Override
    public String toString() {
        return data.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        EducationAndExperience that = (EducationAndExperience) object;
        return Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    public List<DataAboutPerson> getData() {
        return data;
    }
}
