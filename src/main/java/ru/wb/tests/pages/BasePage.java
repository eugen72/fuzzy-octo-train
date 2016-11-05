package ru.wb.tests.pages;

import com.google.common.base.Predicate;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.wb.tests.utils.Browser;
import ru.wb.tests.utils.PageDescribes;
import java.lang.reflect.Field;


public class BasePage extends Browser {
    public static final int WAITING_TIME_OUT_IN_SECONDS = 3;
    public final WebDriver driver;
    public Logger logger;

    public BasePage() {
        logger = Logger.getLogger(getClass());
        driver = getCurrentDriver();
        isThisPage(getClass());
    }

    private void isThisPage(Class clazz) {
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            PageDescribes pageDescribes = field.getAnnotation(PageDescribes.class);
            if (field.getType().equals(By.class)) {
                if (pageDescribes != null && pageDescribes.ignoreThisWhenCheckingInitPage()) {
                    continue;
                }
                try {
                    logger.info("Проверяем наличие элемента: " + field.getName());
                    waitForPageLoad();
                    By locator = (By) field.get(clazz);
                    if (!isElementPresent(locator)) {
                        throw new RuntimeException("Не похоже, что это именно та страница, которую ожидаем: " + clazz.getSimpleName());
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void waitForPageLoad() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        Predicate<WebDriver> pageLoaded = input -> (
                (JavascriptExecutor) input).executeScript("return document.readyState").equals("complete");
        wait.until(pageLoaded);
    }

    public void waitForAjax()
    {
        while (true)
        {
            Boolean ajaxIsComplete = (Boolean) ((JavascriptExecutor)driver).executeScript("return jQuery.active == 0");
            logger.info("Ajax: " + ajaxIsComplete);
            if (ajaxIsComplete){
                break;
            }
            sleep(1);
        }
    }

    public static void sleep(int seconds) {
        try {
            Thread.sleep(1000*seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitForElementVisibility(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, WAITING_TIME_OUT_IN_SECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForElementInvisibility(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, WAITING_TIME_OUT_IN_SECONDS);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public boolean isElementPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    public void type(By locator, String text) {
        logger.info(String.format("Вводим значение '%s' в поле с локатором \"%s\"", text, locator.toString()));
        driver.findElement(locator).sendKeys(text);
    }

    public void click(By locator) {
        logger.info(String.format("Кликаем по кнопке с локатором \"%s\"", locator.toString()));
        driver.findElement(locator).click();
    }

    public boolean isTextPresent(String text) {
        logger.info("Проверяем отображение текста: " + text);
        return driver.findElement(By.xpath(String.format("//*[contains(.,'%s')]", text))).isDisplayed();
    }
}
