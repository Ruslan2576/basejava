package ru.javawebinar.basejava.model;

public class DataAboutPerson {
    private final String date;
    private final String nameOrganization;
    private final String post;
    private final String responsibilities;

    public DataAboutPerson(String date, String nameOrganization, String post, String responsibilities) {
        this.date = date;
        this.nameOrganization = nameOrganization;
        this.post = post;
        this.responsibilities = responsibilities;
    }

    public String getDate() {
        return date;
    }

    public String getNameOrganization() {
        return nameOrganization;
    }

    public String getPost() {
        return post;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    @Override
    public String toString() {
        return "\t\t\t\t\t" + nameOrganization + "\n" + date + "\t" + post + "\n" + responsibilities + "\n\n";
    }
}
