package ru.wb.tests.dto;

import org.apache.commons.lang3.RandomStringUtils;

public class GmailLetter {
    private String to;
    private String subject;
    private String text;

    public GmailLetter(String to) {
        this(to,
            "Тема письма " + RandomStringUtils.randomAlphabetic(10),
            "Тело письма " + RandomStringUtils.randomAlphabetic(100));
    }

    public GmailLetter(String to, String subject, String text) {
        this.to = to;
        this.subject = subject;
        this.text = text;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
