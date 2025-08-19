package ru.javawebinar.basejava.storage.strategy;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import ru.javawebinar.basejava.model.Company;
import ru.javawebinar.basejava.model.CompanySection;
import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.ListSection;
import ru.javawebinar.basejava.model.Period;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.model.Section;
import ru.javawebinar.basejava.model.SectionType;
import ru.javawebinar.basejava.model.TextSection;

public class DataStreamSerializer implements SerializerStrategy {
    @Override
    public Resume doRead(InputStream inputStream) throws IOException {
        try (DataInputStream dis = new DataInputStream(inputStream)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);

            // Читаем контакты
            int contactsSize = dis.readInt();
            for (int i = 0; i < contactsSize; i++) {
                resume.setContacts(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

            // Читаем секции
            int sectionSize = dis.readInt();
            for (int i = 0; i < sectionSize; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                Section section;
                switch (sectionType) {
                    case PERSONAL, OBJECTIVE -> section = new TextSection(dis.readUTF());
                    case ACHIEVEMENT, QUALIFICATIONS -> {
                        int listSize = dis.readInt();
                        List<String> items = new ArrayList<>(listSize);
                        for (int j = 0; j < listSize; j++) {
                            items.add(dis.readUTF());
                        }
                        section = new ListSection(items);
                    }
                    case EXPERIENCE, EDUCATION -> {
                        int companySize = dis.readInt();
                        List<Company> companies = new ArrayList<>(companySize);
                        for (int j = 0; j < companySize; j++) {
                            String name = dis.readUTF();
                            String website = dis.readUTF();
                            int periodSize = dis.readInt();
                            List<Period> periods = new ArrayList<>(periodSize);
                            for (int k = 0; k < periodSize; k++) {
                                String title = dis.readUTF();
                                String description = dis.readUTF();
                                String startDate = dis.readUTF();
                                String endDate = dis.readUTF();
                                periods.add(new Period(LocalDate.parse(startDate), LocalDate.parse(endDate),
                                        title, description));
                            }
                            companies.add(new Company(name, website, periods));
                        }
                        section = new CompanySection(companies);
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + sectionType);
                }
                resume.setSections(sectionType, section);
            }
            return resume;
        }
    }

    @Override
    public void doWrite(Resume resume, OutputStream outputStream) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(outputStream)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());

            // Записать контакты
            writeData(dos, resume.getContacts().entrySet(), element -> {
                dos.writeUTF(element.getKey().name());
                dos.writeUTF(element.getValue());
            });

            // Записать секции
            writeData(dos, resume.getSections().entrySet(), element -> {
                SectionType sectionType = element.getKey();
                Section section = element.getValue();
                dos.writeUTF(sectionType.name());
                switch (sectionType) {
                    case PERSONAL, OBJECTIVE -> dos.writeUTF(((TextSection) section).getContent());
                    case ACHIEVEMENT, QUALIFICATIONS -> {
                        List<String> items = ((ListSection) section).getStrings();
                        dos.writeInt(items.size());
                        for (String item : items) {
                            dos.writeUTF(item);
                        }
                    }
                    case EXPERIENCE, EDUCATION -> {
                        List<Company> companies = ((CompanySection) section).getCompanies();
                        dos.writeInt(companies.size());
                        for (Company company : companies) {
                            dos.writeUTF(company.getName());
                            dos.writeUTF(company.getWebsite());
                            List<Period> periods = company.getPeriods();
                            dos.writeInt(periods.size());
                            for (Period period : periods) {
                                dos.writeUTF(period.getTitle());
                                dos.writeUTF(period.getDescription());
                                dos.writeUTF(period.getStartDate().toString());
                                dos.writeUTF(period.getEndDate().toString());
                            }
                        }
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + sectionType);
                }
            });
        }
    }

    @FunctionalInterface
    public interface Writer<T> {
        void writeWithException(T element) throws IOException;
    }

    private <T> void writeData(DataOutputStream dos, Collection<T> collection, Writer<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T element : collection) {
            writer.writeWithException(element);
        }
    }
}
