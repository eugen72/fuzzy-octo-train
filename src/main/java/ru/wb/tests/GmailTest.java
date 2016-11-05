package ru.wb.tests;

import org.testng.annotations.Test;
import ru.wb.tests.dto.GmailLetter;
import ru.wb.tests.dto.GmailUser;
import ru.wb.tests.pages.gmail.GmailMailPage;
import ru.wb.tests.pages.gmail.GmailMainPage;
import ru.wb.tests.utils.BaseTest;
import ru.wb.tests.utils.Host;

public class GmailTest extends BaseTest {


    @Test(description = "Проверка выполнения теста в двух разных окнах браузера на примере почты Gmail")
    public void checkLettersInTwoBrowsers() {
        final GmailUser gmailUser1 = new GmailUser("john.0783778@gmail.com", "6tfc%RDX");
        final GmailUser gmailUser2 = new GmailUser("joe.06583778@gmail.com", "8uhb&YGV");
        final GmailLetter fistLetter = new GmailLetter(gmailUser2.getEmail());
        final GmailLetter responseOnFirstLetter = new GmailLetter(gmailUser1.getEmail());
        final GmailLetter responseOnSecondLetter = new GmailLetter(gmailUser2.getEmail());

        GmailMainPage gmailMainPage1 = open(Host.GMAIL);
        //1. Пользователь1 логинится в почту Gmail и создает письмо на пользователя2
        GmailMailPage gmailMailPage1 = gmailMainPage1.clickSignIn().signIn(gmailUser1).clickByCreateNewLetter()
                .createLetter(fistLetter);
        //2. Пользователь2 заходит в свою почту параллельно и проверяет наличие новых писем
        initAnotherDriver();
        switchDriver();
        GmailMainPage gmailMainPage2 = open(Host.GMAIL);
        GmailMailPage gmailMailPage2 = gmailMainPage2.clickSignIn().signIn(gmailUser2);
        gmailMailPage2.waitForLetter(fistLetter.getTo(), fistLetter.getSubject());
        //3. Пользователь2 отправляет ответ на письмо пользователю1
        gmailMailPage2.clickByLetter(gmailUser1.getEmail(), fistLetter.getSubject())
                .clickByReplyButton().makeReply(responseOnFirstLetter.getText());
        //4. Пользователь1 получает письмо и отправляет ответ пользователю2
        switchDriver();
        getCurrentDriver().navigate().refresh();
        gmailMailPage1.clickByLetter(gmailUser2.getEmail())
                .clickByReplyButton().makeReply(responseOnSecondLetter.getText());
    }
}
