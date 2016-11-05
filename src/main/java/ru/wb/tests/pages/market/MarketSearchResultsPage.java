package ru.wb.tests.pages.market;

import org.openqa.selenium.By;
import ru.wb.tests.pages.BasePage;

public class MarketSearchResultsPage extends BasePage {

    public static final By MOB_PHONES = By.xpath("//*[@class='n-brands-note-item__text'][text()='Мобильные телефоны']/..");

    public MarketCatalogPage clickByMobile() {
        logger.info("Кликаем по ссылке 'Мобильные телефоны'");
        driver.findElement(MOB_PHONES).click();
        waitForAjax();
        return new MarketCatalogPage();
    }
}
