package ru.wb.tests.utils;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest extends Browser {

    @BeforeClass
    public void setUp() throws Exception {
        initCurrentDriver();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        stopBrowser();
    }

}
