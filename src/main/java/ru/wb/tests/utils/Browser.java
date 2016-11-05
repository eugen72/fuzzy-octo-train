package ru.wb.tests.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Browser {
    private static WebDriver driver;
    private static WebDriver secondDriver;
    private static WebDriver buffDriver;
    final static Logger logger = Logger.getLogger(Browser.class);

    public void initCurrentDriver() {
    driver = init();
    }

    public static WebDriver init() {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        return driver;
    }

    public static WebDriver getCurrentDriver() {
        return driver;
    }

    public void initAnotherDriver() {
    secondDriver = init();
    }

    public static void switchDriver() {
        if (!getCurrentDriver().equals(secondDriver)) {
            logger.info("Switch to browser #2");
            buffDriver = driver;
            driver = secondDriver;
            secondDriver = buffDriver;
        } else {
            logger.info("Switch to browser #1");
            secondDriver = driver;
            driver = buffDriver;
        }
    }

    public void stopBrowser() throws Exception {
        driver.close();
        if (secondDriver!=null) {
            secondDriver.close();
        }

    }

    public static <T>T open(Host host) {
        logger.info("Открываем в браузере ссылку " + host.getUrl());
        getCurrentDriver().navigate().to(host.getUrl());
        try {
            return (T) host.getClazz().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
