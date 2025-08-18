package ru.javawebinar.basejava.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Company implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String name;
    private String website;
    private List<Period> periods;

    public Company() {
    }

    public Company(String name, String website, List<Period> periods) {
        Objects.requireNonNull(name, "name mustn't be null");
        Objects.requireNonNull(periods, "periods mustn't be null");
        this.name = name;
        this.website = website;
        this.periods = periods;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public String getName() {
        return name;
    }

    public String getWebsite() {
        return website;
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
