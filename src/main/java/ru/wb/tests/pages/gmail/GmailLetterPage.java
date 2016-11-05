package ru.wb.tests.pages.gmail;

import org.openqa.selenium.By;
import ru.wb.tests.utils.PageDescribes;
import ru.wb.tests.pages.BasePage;

public class GmailLetterPage extends BasePage {
    public static final By REPLY_BUTTON = By.xpath("//div[@class='amn']/span[.='Ответить']");
    @PageDescribes(ignoreThisWhenCheckingInitPage = true)
    public static final By TEXT_FIELD = By.xpath("//div[@aria-label='Тело письма']");
    @PageDescribes(ignoreThisWhenCheckingInitPage = true)
    public static final By SEND_BUTTON = By.xpath("//div[contains(text(),'Отправить')]");

    public GmailLetterPage clickByReplyButton() {
        click(REPLY_BUTTON);
        return this;
    }

    public GmailLetterPage makeReply(String text) {
        type(TEXT_FIELD, text);
        click(SEND_BUTTON);
        return this;
    }


}
