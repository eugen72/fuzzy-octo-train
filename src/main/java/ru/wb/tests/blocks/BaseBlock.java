package ru.wb.tests.blocks;

import org.openqa.selenium.WebDriver;
import ru.wb.tests.utils.Browser;

public class BaseBlock extends Browser{
    public final WebDriver driver;

    public BaseBlock() {
    driver = getCurrentDriver();
    }
}
