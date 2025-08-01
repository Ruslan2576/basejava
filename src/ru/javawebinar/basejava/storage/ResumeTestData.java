package ru.javawebinar.basejava.storage;

import java.time.LocalDate;
import java.util.List;
import ru.javawebinar.basejava.model.Company;
import ru.javawebinar.basejava.model.CompanySection;
import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.ListSection;
import ru.javawebinar.basejava.model.Period;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.model.SectionType;
import ru.javawebinar.basejava.model.TextSection;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume resume = new Resume("Григорий Кислин");
        System.out.println(resume.getFullName());
        List<String> achievements = List.of("""
                Организация команды и успешная реализация Java проектов для
                сторонних заказчиков: приложения автопарк на стеке Spring Cloud/
                микросервисы, система мониторинга показателей спортсменов на Spring
                Boot, участие в проекте МЭШ на Play-2, многомодульный Spring Boot +
                Vaadin проект для комплексных DIY смет
                С 2013 года: разработка проектов "Разработка Web приложения","Java
                Enterprise", "Многомодульный maven. Многопоточность. XML
                (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие
                (JMS/AKKA)". Организация онлайн стажировок и ведение проектов.
                Более 3500 выпускников.
                Реализация двухфакторной аутентификации для онлайн платформы
                управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google
                Authenticator, Jira, Zendesk.
                Налаживание процесса разработки и непрерывной интеграции ERP
                системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP.
                Разработка приложения управления окружением на стеке:
                Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации
                различных ERP модулей, интеграция CIFS/SMB java сервера.
                Реализация c нуля Rich Internet Application приложения на стеке
                технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet,
                HTML5, Highstock для алгоритмического трейдинга.
                Создание JavaEE фреймворка для отказоустойчивого взаимодействия
                слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS
                Glassfish). Сбор статистики сервисов и информации о состоянии через
                систему мониторинга Nagios. Реализация онлайн клиента для
                администрирования и мониторинга системы по JMX (Jython/ Django).
                Реализация протоколов по приему платежей всех основных платежных
                системы России (Cyberplat, Eport, Chronopay, Сбербанк,
                Белоруcсии(Erip, Osmp) и Никарагуа.""");

        List<String> qualifications = List.of("""
                JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2
                Version control: Subversion, Git, Mercury, ClearCase, Perforce
                DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2,
                Oracle, MySQL, SQLite, MS SQL, HSQLDB
                Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy
                XML/XSD/XSLT, SQL, C/C++, Unix shell scripts
                Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor,
                MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate,
                EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin,
                Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium
                (htmlelements).
                Python: Django.
                JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js
                Scala: SBT, Play2, Specs2, Anorm, Spray, Akka
                Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail,
                JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS,
                SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1,
                OAuth2, JWT.
                Инструменты: Maven + plugin development, Gradle, настройка Ngnix
                администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher,
                Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer
                Отличное знание и опыт применения концепций ООП, SOA, шаблонов
                проектрирования, архитектурных шаблонов, UML, функционального
                программирования
                Родной русский, английский "upper intermediate\"""");

        resume.setSections(SectionType.ACHIEVEMENT, new ListSection(achievements));
        resume.setSections(SectionType.QUALIFICATIONS, new ListSection(qualifications));

        resume.setContacts(ContactType.PHONE_NUMBER, "+7(921) 855-0482");
        resume.setContacts(ContactType.SKYPE, "skype:grigory.kislin");
        resume.setContacts(ContactType.MAIL, "gkislin@yandex.ru");
        resume.setContacts(ContactType.LINKEDIN, "");
        resume.setContacts(ContactType.GITHUB, "");
        resume.setContacts(ContactType.STACKOVERFLOW, "");

        resume.setSections(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного" +
                " обучения по Java Web и Enterprise технологиям"));
        resume.setSections(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная" +
                " логика, креативность, инициативность. Пурист кода и архитектуры."));

        String str1 = """
                Создание, организация и проведение Java онлайн проектов и стажировок.
                """;

        String str2 = """
                Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API,
                Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная
                аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.
                 """;

        String str3 = """
                Организация процесса разработки системы ERP для разных окружений: релизная политика,
                версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway),
                конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и
                серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C
                (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html).
                Интеграция Alfresco JLAN для online редактирование из браузера документов MS
                Office. Maven + plugin development, Ant, Apache Commons, Spring security,
                Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell
                remote scripting via ssh tunnels, PL/Python""";

        String str4 = """
                Участие в проекте Deutsche Bank CRM (WebLogic,
                Hibernate, Spring, Spring MVC, SmartGWT, GWT,
                Jasper, Oracle). Реализация клиентской и серверной
                части CRM. Реализация RIA-приложения для
                администрирования,мониторинга и анализа
                результатов в области алгоритмического трейдинга
                JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT),
                Highstock, Commet, HTML5.
                """;

        String str5 = """
                Дизайн и имплементация Java EE фреймворка для
                отдела "Платежные Системы" (GlassFish v2.1, v3,
                OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX,
                JMS, Maven2). Реализация администрирования,
                статистики и мониторинга фреймворка. Разработка
                online JMX клиента (Python/ Jython, Django, ExtJS)
                """;

        String str6 = """
                Реализация клиентской (Eclipse RCP) и серверной
                (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей
                кластерного J2EE приложения (OLAP, Data mining).
                """;

        String str7 = """
                Разработка информационной модели,
                проектирование интерфейсов, реализация и отладка
                ПО на мобильной IN платформе Siemens @vantage
                (Java, Unix).
                """;

        String str8 = """
                Тестирование, отладка, внедрение ПО цифровой
                телефонной станции Alcatel 1000 S12 (CHILL,
                ASM).
                """;

        var period1 = new Period(LocalDate.of(2013, 10, 1),
                LocalDate.now(), "Автор проекта", str1);
        var period2 = new Period(LocalDate.of(2014, 10, 1),
                LocalDate.of(2016, 1, 1), "Старший разработчик", str2);
        var period3 = new Period(LocalDate.of(2012, 4, 1),
                LocalDate.of(2014, 10, 1), "Java архитектор", str3);
        var period4 = new Period(LocalDate.of(2010, 12, 1),
                LocalDate.of(2012, 4, 1), "Ведущий программист", str4);
        var period5 = new Period(LocalDate.of(2008, 6, 1),
                LocalDate.of(2010, 12, 1), "Ведущий специалист", str5);
        var period6 = new Period(LocalDate.of(2012, 4, 1),
                LocalDate.of(2007, 3, 1), "Разработчик ПО", str6);
        var period7 = new Period(LocalDate.of(2005, 1, 1),
                LocalDate.of(2007, 2, 1), "Разработчик ПО", str7);
        var period8 = new Period(LocalDate.of(1997, 9, 1),
                LocalDate.of(2005, 1, 1),
                "Инженер по аппаратному и программному тестированию", str8);

        var company1 = new Company("Java Online Projects", "", List.of(period1));
        var company2 = new Company("Wrike", "", List.of(period2));
        var company3 = new Company("RIT Center", "", List.of(period3));
        var company4 = new Company("Luxoft (Deutsche Bank)", "", List.of(period4));
        var company5 = new Company("Yota", "", List.of(period5));
        var company6 = new Company("Enkata", "", List.of(period6));
        var company7 = new Company("Siemens AG", "", List.of(period7));
        var company8 = new Company("Alcatel", "", List.of(period8));

        var experience = new CompanySection(List.of(company1, company2, company3, company4, company5,
                company6, company7, company8));
        resume.setSections(SectionType.EXPERIENCE, experience);

        var period9 = new Period(LocalDate.of(2023, 3, 1),
                LocalDate.of(2023, 5, 1), "",
                "'Functional Programming Principles in Scala' by Martin Odersky\n");
        var period10 = new Period(LocalDate.of(2011, 3, 1),
                LocalDate.of(2011, 4, 1), "",
                "Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.'\n");
        var period11 = new Period(LocalDate.of(2005, 1, 1),
                LocalDate.of(2005, 4, 1), "",
                "3 месяца обучения мобильным IN сетям (Берлин)\n");

        var period12 = new Period(LocalDate.of(1997, 9, 1),
                LocalDate.of(1998, 3, 1), "",
                "6 месяцев обучения цифровым телефонным сетям (Москва)\n");

        var period13 = new Period(LocalDate.of(1993, 9, 1),
                LocalDate.of(1996, 7, 1), "",
                "Аспирантура (программист С, С++)\n");
        var period14 = new Period(LocalDate.of(1987, 9, 1),
                LocalDate.of(1993, 7, 1), "",
                "Инженер (программист Fortran, C)\n");
        var period15 = new Period(LocalDate.of(1984, 9, 1),
                LocalDate.of(1987, 6, 1), "",
                "Закончил с отличием");

        var company9 = new Company("Coursera", "", List.of(period9));
        var company10 = new Company("Luxoft", "", List.of(period10));
        var company11 = new Company("Siemens AG", "", List.of(period11));
        var company12 = new Company("Alcatel", "", List.of(period12));
        var company13 = new Company("""
                Санкт-Петербургский национальный
                исследовательский университет
                информационных технологий,
                механики и оптики""", "", List.of(period13, period14));
        var company14 = new Company("Заочная физико-техническая школа\n" +
                "при МФТИ", "", List.of(period15));

        var education = new CompanySection(List.of(company9, company10, company11, company12, company13,
                company14));
        resume.setSections(SectionType.EDUCATION, education);
        printResume(resume);
    }

    public static void printResume(Resume resume) {
        var contacts = resume.getContacts();
        for (ContactType key : contacts.keySet()) {
            System.out.println(key.getTitle() + " " + contacts.get(key));
        }

        System.out.println();
        var sections = resume.getSections();
        for (SectionType key : sections.keySet()) {
            System.out.println(key.getTitle() + "\n" + sections.get(key).toString()
                    .replaceAll("[\\[\\]]", "") + "\n");
        }
    }

    public static Resume createResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        resume.setContacts(ContactType.PHONE_NUMBER, "+7(921) 855-0482");
        resume.setContacts(ContactType.SKYPE, "skype:grigory.kislin");
        resume.setContacts(ContactType.MAIL, "gkislin@yandex.ru");
        resume.setContacts(ContactType.LINKEDIN, "");
        resume.setContacts(ContactType.GITHUB, "");
        resume.setContacts(ContactType.STACKOVERFLOW, "");

        resume.setSections(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная" +
                " логика, креативность, инициативность. Пурист кода и архитектуры."));
        resume.setSections(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного" +
                " обучения по Java Web и Enterprise технологиям"));

        resume.setSections(SectionType.ACHIEVEMENT, new ListSection(List.of("achievements")));
        resume.setSections(SectionType.QUALIFICATIONS, new ListSection(List.of("qualifications")));

        var period1 = new Period(LocalDate.of(2013, 10, 1),
                LocalDate.now(), "Автор проекта", "str1");
        resume.setSections(SectionType.EXPERIENCE, new CompanySection(List.of(
                new Company("Java Online Projects", "", List.of(period1)))));

        var period9 = new Period(LocalDate.of(2023, 3, 1),
                LocalDate.of(2023, 5, 1), "",
                "'Functional Programming Principles in Scala' by Martin Odersky\n");
        resume.setSections(SectionType.EDUCATION, new CompanySection(List.of(new Company("Alcatel",
                "", List.of(period9)))));
        return resume;
    }
}