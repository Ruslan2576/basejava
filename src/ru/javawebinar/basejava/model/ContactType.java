package ru.javawebinar.basejava.model;

public enum ContactType {
    PHONE_NUMBER("Тел.: "),
    SKYPE("Skype: "){
        public String toHtml0(String value) {
            return "<a href='skype:" + value + "'>" + value + "</a>";
        }
    },
    MAIL("Почта: "){
        public String toHtml0(String value) {
            return "<a href='mailto:" + value + "'>" + value + "</a>";
        }
    },
    LINKEDIN("Профиль LinkedIn"),
    GITHUB("Профиль GitHub"),
    STACKOVERFLOW("Профиль StackOverflow");

    private final String title;

    public String getTitle() {
        return title;
    }

    ContactType(String title) {
        this.title = title;
    }

    protected String toHtml0(String value) {
        return title + ": " + value;
    }

    public String toHtml(String value) {
        return value == null ? "" : toHtml0(value);
    }
}

