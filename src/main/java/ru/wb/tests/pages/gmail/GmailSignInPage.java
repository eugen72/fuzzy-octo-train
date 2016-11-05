package ru.wb.tests.pages.gmail;

import org.openqa.selenium.By;
import ru.wb.tests.utils.PageDescribes;
import ru.wb.tests.dto.GmailUser;

public class GmailSignInPage extends GmailMainPage {

    public static final By EMAIL_FIELD = By.id("Email");
    public static final By NEXT_BUTTON = By.id("next");
    @PageDescribes(ignoreThisWhenCheckingInitPage = true)
    public static final By PASSWORD_FIELD = By.id("Passwd");
    public static final By SUBMIT_BUTTON = By.id("signIn");

    public GmailMailPage signIn(GmailUser user) {
        type(EMAIL_FIELD, user.getEmail());
        click(NEXT_BUTTON);
        type(PASSWORD_FIELD, user.getPassword());
        click(SUBMIT_BUTTON);
        return new GmailMailPage();
    }
}
