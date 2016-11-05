package ru.wb.tests.blocks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchFilterBlock extends BaseBlock {
    private static final By MAIN_LOCATOR = By.className("n-filter-panel-aside__content");
    public static final By APPLY_BUTTON = By.xpath("//div[contains(@class,'apply')]");

    private WebElement getMainBlock() {
        return driver.findElement(MAIN_LOCATOR);
    }

    public void clickByText(final String text) {
        getMainBlock().findElement(By.xpath(String.format("//label[text()='%s']", text)))
        .click();
    }

    public void clickApplyFilter() {
        getMainBlock().findElement(APPLY_BUTTON).click();
    }
}
