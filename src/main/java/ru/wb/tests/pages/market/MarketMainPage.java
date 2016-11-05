package ru.wb.tests.pages.market;

import org.openqa.selenium.By;
import ru.wb.tests.pages.BasePage;

public class MarketMainPage extends BasePage {

    public static final By SEARCH_FIELD = By.id("header-search");
    public static final By SEARCH_BUTTON = By.xpath("//*[@class='search2__button']/button");

    public MarketSearchResultsPage doSearch(String searchQuery) {
        logger.info(String.format("Вводим значение '%s' в строку поиска", searchQuery));
        driver.findElement(SEARCH_FIELD).sendKeys(searchQuery);
        clickSearchButton();
        waitForAjax();
        waitForPageLoad();
        return new MarketSearchResultsPage();
    }

    public void clickSearchButton() {
        waitForElementVisibility(SEARCH_BUTTON);
        driver.findElement(SEARCH_BUTTON).click();
    }
}
