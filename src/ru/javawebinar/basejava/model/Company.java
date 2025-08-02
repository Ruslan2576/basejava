package ru.javawebinar.basejava.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Company implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String name;
    private final String website;
    private final List<Period> periods;

    public Company(String name, String website, List<Period> periods) {
        Objects.requireNonNull(name, "name mustn't be null");
        Objects.requireNonNull(periods, "periods mustn't be null");
        this.name = name;
        this.website = website;
        this.periods = periods;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Company company = (Company) object;
        return Objects.equals(name, company.name) && Objects.equals(website, company.website) &&
                Objects.equals(periods, company.periods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, website, periods);
    }

    @Override
    public String toString() {
        return name + "\n" + periods + "\n";
    }
}
