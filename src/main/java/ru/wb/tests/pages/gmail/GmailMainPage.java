package ru.wb.tests.pages.gmail;

import org.openqa.selenium.By;
import ru.wb.tests.pages.BasePage;

public class GmailMainPage extends BasePage {

    public static final By SIGN_IN = By.id("gmail-sign-in");

    public GmailSignInPage clickSignIn() {
        driver.findElement(SIGN_IN).click();
        return new GmailSignInPage();
    }
}
