package ru.wb.tests.pages.gmail;

import org.openqa.selenium.By;
import ru.wb.tests.dto.GmailLetter;
import ru.wb.tests.utils.PageDescribes;
import ru.wb.tests.pages.BasePage;

public class GmailMailPage extends BasePage {

    public static final By CREATE_NEW_LETTER = By.xpath("//div[contains(text(),'НАПИСАТЬ')]");
    @PageDescribes(ignoreThisWhenCheckingInitPage = true)
    public static final By TO_FIELD = By.xpath("//*[@aria-label='Кому']");
    @PageDescribes(ignoreThisWhenCheckingInitPage = true)
    public static final By TEXT_FIELD = By.xpath("//div[@aria-label='Тело письма']");
    @PageDescribes(ignoreThisWhenCheckingInitPage = true)
    public static final By SUBJECT_FIELD = By.xpath("//*[@name='subjectbox']");
    @PageDescribes(ignoreThisWhenCheckingInitPage = true)
    public static final By SEND_BUTTON = By.xpath("//div[contains(text(),'Отправить')]");
    public static final String LETTER_BY_EMAIL_PATTERN = "//tbody/tr[//*[@email='%s']]";
    public static final String SUBJECT_PATTERN = "//*[contains(.,'%s')]";

    public GmailMailPage clickByCreateNewLetter() {
        click(CREATE_NEW_LETTER);
        return this;
    }

    public GmailMailPage createLetter(String to, String subject, String text) {
        type(TO_FIELD, to);
        type(SUBJECT_FIELD, subject);
        type(TEXT_FIELD, text);
        click(SEND_BUTTON);
        return this;
    }

    public GmailMailPage createLetter(GmailLetter letter) {
        return createLetter(letter.getTo(), letter.getSubject(), letter.getText());
    }

    public GmailLetterPage clickByLetter(String email, String subject) {
        click(By.xpath(String.format(LETTER_BY_EMAIL_PATTERN + SUBJECT_PATTERN, email, subject)));
        return new GmailLetterPage();
    }

    public GmailLetterPage clickByLetter(String email) {
        click(By.xpath(String.format("//*[@email='%s']/../..", email)));
        return new GmailLetterPage();
    }

    public void waitForLetter(String email, String subject) {
        waitForElementVisibility(By.xpath(String.format(LETTER_BY_EMAIL_PATTERN + SUBJECT_PATTERN, email, subject)), 15);
    }
}
