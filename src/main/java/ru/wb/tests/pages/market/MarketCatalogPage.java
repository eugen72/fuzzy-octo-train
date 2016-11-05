package ru.wb.tests.pages.market;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.wb.tests.utils.PageDescribes;
import ru.wb.tests.blocks.SearchFilterBlock;
import ru.wb.tests.pages.BasePage;

import java.util.List;

public class MarketCatalogPage extends BasePage {
    @PageDescribes(ignoreThisWhenCheckingInitPage = true)
    public static final By NEXT_PAGE_BUTTON = By.xpath("//*[contains(@class,'n-pager')][contains(@class,'next')]");
    public static final By GOODS_TITLE_LIST_VIEW = By.xpath("//*[@class='snippet-card__header-text']");
    @PageDescribes(ignoreThisWhenCheckingInitPage = true)
    public static final By SPINNER = By.xpath("//*[contains(@class,'spin')]");
    private SearchFilterBlock searchFilterBlock = new SearchFilterBlock();

    public MarketCatalogPage clickToNextPage() {
        driver.findElement(NEXT_PAGE_BUTTON)
                .click();
        return this;
    }

    public boolean isGoodPresentByTitle(final String text) {
        List<WebElement> elements = driver.findElements(GOODS_TITLE_LIST_VIEW);
        return elements.stream().anyMatch(e -> e.getText().equals(text));
    }

    public void checkForGoodIsPresent(final String title) {
        while (!isGoodPresentByTitle(title)) {
            clickToNextPage();
        }
    }

    public MarketCatalogPage doSearchFilter() {
        searchFilterBlock.clickByText("Windows");
        searchFilterBlock.clickByText("белый");
        searchFilterBlock.clickApplyFilter();
        waitForElementVisibility(SPINNER);
        waitForElementVisibility(GOODS_TITLE_LIST_VIEW);
        waitForAjax();
        return this;
    }
}
