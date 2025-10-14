package ru.javawebinar.basejava.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.Reader;
import java.io.Writer;
import java.time.LocalDate;
import ru.javawebinar.basejava.model.Section;

public class JsonParser {
    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(Section.class, new JsonSectionAdapter<>())
            .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
            .create();

    public static <T> T read(Reader reader, Class<T> clazz) {
        return GSON.fromJson(reader, clazz);
    }

    public static <T> void write(T object, Writer writer) {
        GSON.toJson(object, writer);
    }

    public static <T> T read(String section_value, Class<T> clazz) {
        return GSON.fromJson(section_value, clazz);
    }

    public static <T> String write(T object, Class<T> clazz) {
        return GSON.toJson(object, clazz);
    }
}