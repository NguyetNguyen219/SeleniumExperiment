package com.nguyet.test.selenium4test;

import com.nguyet.test.BaseTest;
import com.nguyet.test.core.DriverWrapper;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class SeleniumBaseTest extends BaseTest {

    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void preCondition() {
        driver = DriverWrapper.getDriver();
        driver.navigate().to("https://www.booking.com");
        driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void postCondition() {
        driver.quit();
    }
}
