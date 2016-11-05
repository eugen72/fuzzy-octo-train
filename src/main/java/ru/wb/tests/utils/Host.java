package ru.wb.tests.utils;

import ru.wb.tests.pages.gmail.GmailMainPage;
import ru.wb.tests.pages.gmail.GmailSignInPage;
import ru.wb.tests.pages.market.MarketMainPage;

public enum Host {
    MARKET("https://market.yandex.ru", MarketMainPage.class),
    GMAIL("https://www.google.com/intl/ru/mail/help/about.html", GmailMainPage.class);

    private final String url;
    private final Class clazz;

    Host(String url, Class clazz) {
        this.url = url;
        this.clazz = clazz;
    }

    public String getUrl() {
        return url;
    }

    public Class getClazz() {
        return clazz;
    }
}
