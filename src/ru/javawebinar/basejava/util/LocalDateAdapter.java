package ru.javawebinar.basejava.util;

import java.time.LocalDate;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    @Override
    public LocalDate unmarshal(String s) {
        return LocalDate.parse(s);
    }

    @Override
    public String marshal(LocalDate localDate) {
        return localDate.toString();
    }
}
