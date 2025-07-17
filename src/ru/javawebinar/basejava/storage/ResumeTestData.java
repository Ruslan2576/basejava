package ru.javawebinar.basejava.storage;

import java.time.LocalDate;
import java.util.List;
import ru.javawebinar.basejava.model.Company;
import ru.javawebinar.basejava.model.CompanySection;
import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.ContentSection;
import ru.javawebinar.basejava.model.Period;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.model.SectionType;
import ru.javawebinar.basejava.model.StringsSection;

public class ResumeTestData {
    public static void main(String[] args) {
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

        Resume resume = new Resume("Grigory");
        resume.setContacts(ContactType.PHONE_NUMBER, "+7(921) 855-0482");
        resume.setContacts(ContactType.SKYPE, "skype:grigory.kislin");
        resume.setContacts(ContactType.MAIL, "gkislin@yandex.ru");
        resume.setContacts(ContactType.LINKEDIN, "");
        resume.setContacts(ContactType.GITHUB, "");
        resume.setContacts(ContactType.STACKOVERFLOW, "");

        resume.setSections(SectionType.OBJECTIVE, new ContentSection("Ведущий стажировок и корпоративного" +
                " обучения по Java Web и Enterprise технологиям"));
        resume.setSections(SectionType.PERSONAL, new ContentSection("Аналитический склад ума, сильная" +
                " логика, креативность, инициативность. Пурист кода и архитектуры."));

        resume.setSections(SectionType.ACHIEVEMENT, new StringsSection(achievements));
        resume.setSections(SectionType.QUALIFICATIONS, new StringsSection(qualifications));

        String str1 = """
                Создание, организация и проведение Java онлайн проектов и стажировок.
                """;

        String str2 =
                """
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

        var period1 = new Period(LocalDate.of(2013, 10, 1),
                LocalDate.now(), "Автор проекта", str1);
        var period2 = new Period(LocalDate.of(2014, 10, 1),
                LocalDate.of(2016, 1, 1), "Старший разработчик", str2);
        var period3 = new Period(LocalDate.of(2012, 4, 1),
                LocalDate.of(2014, 10, 1), "Java архитектор", str3);

        var company1 = new Company("Java Online Projects", "", List.of(period1));
        var company2 = new Company("Wrike", "", List.of(period2));
        var company3 = new Company("RIT Center", "", List.of(period3));

        var experience = new CompanySection(List.of(company1, company2, company3));
        resume.setSections(SectionType.EXPERIENCE, experience);

        var period4 = new Period(LocalDate.of(2023, 3, 1),
                LocalDate.of(2023, 5, 1), "",
                "'Functional Programming Principles in Scala' by Martin Odersky");
        var period5 = new Period(LocalDate.of(2011, 3, 1),
                LocalDate.of(2011, 4, 1), "", "\n" +
                "Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.'");
        var period6 = new Period(LocalDate.of(2005, 1, 1),
                LocalDate.of(2005, 4, 1), "", "\n" +
                "3 месяца обучения мобильным IN сетям (Берлин)");

        var company4 = new Company("Coursera", "", List.of(period4));
        var company5 = new Company("Luxoft", "", List.of(period5));
        var company6 = new Company("Siemens AG", "", List.of(period6));

        var education = new CompanySection(List.of(company4, company5, company6));
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
}