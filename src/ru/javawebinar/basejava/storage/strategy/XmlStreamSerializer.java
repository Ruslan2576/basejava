package ru.javawebinar.basejava.storage.strategy;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import javax.swing.text.Position;
import ru.javawebinar.basejava.model.Company;
import ru.javawebinar.basejava.model.CompanySection;
import ru.javawebinar.basejava.model.ListSection;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.model.TextSection;
import ru.javawebinar.basejava.util.XmlParser;

public class XmlStreamSerializer implements SerializerStrategy {
    private final XmlParser xmlParser;

    public XmlStreamSerializer() {
        xmlParser = new XmlParser(Resume.class, Company.class, CompanySection.class,
                TextSection.class, ListSection.class, Position.class);
    }

    @Override
    public Resume doRead(InputStream inputStream) throws IOException {
        try (Reader r = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            return xmlParser.unmarshall(r);
        }
    }

    @Override
    public void doWrite(Resume resume, OutputStream outputStream) throws IOException {
        try (Writer w = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {
            xmlParser.marshall(resume, w);
        }
    }
}
