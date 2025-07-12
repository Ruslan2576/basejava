package ru.javawebinar.basejava.storage;

import java.util.List;

import ru.javawebinar.basejava.model.*;

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

        Resume resume = new Resume("ruslan");
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

        EducationAndExperience experience = new EducationAndExperience();
        String str1 =
                """
                        Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API,
                        Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная
                        аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.
                        """;

        String str2 = """
                         Организация процесса разработки системы ERP для разных окружений: релизная политика,
                         версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway),
                         конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и
                         серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C
                         (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html).
                         Интеграция Alfresco JLAN для online редактирование из браузера документов MS
                         Office. Maven + plugin development, Ant, Apache Commons, Spring security,
                         Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell
                         remote scripting via ssh tunnels, PL/Python""";

        experience.addData(new DataAboutPerson("10/2013 - Сейчас", "Java Online Projects",
                "Автор проекта.", "Создание, организация и проведение Java онлайн проектов и стажировок."));
        experience.addData(new DataAboutPerson("10/2014 - 01/2016", "Wrike",
                "Старший разработчик (backend)", str1));

        experience.addData(new DataAboutPerson("04/2012 - 10/2014", "RIT Center",
                "Java архитектор", str2));
        resume.setSections(SectionType.EXPERIENCE, experience);

        EducationAndExperience education = new EducationAndExperience();
        education.addData(new DataAboutPerson("03/2013 - 05/2013", "Coursera", "",
                "'Functional Programming Principles in Scala' by Martin Odersky"));
        education.addData(new DataAboutPerson("09/1997 - 03/1998", "Alcatel", "",
                "6 месяцев обучения цифровым телефонным сетям (Москва)"));
        resume.setSections(SectionType.EDUCATION, education);

        var contacts = resume.getContacts();
        for (ContactType key : contacts.keySet()) {
            System.out.println(key.getTitle() + " " + contacts.get(key));
        }

        System.out.println();

        var sections = resume.getSections();
        for (SectionType key : sections.keySet()) {
            System.out.println(key.getTitle() + "\n" + sections.get(key).toString().replaceAll("[\\[\\]]", "") + "\n");
        }
    }
}
