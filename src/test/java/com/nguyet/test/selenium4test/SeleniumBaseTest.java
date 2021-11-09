package com.nguyet.test.selenium4test;

import com.nguyet.test.BaseTest;
import com.nguyet.test.core.DriverWrapper;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class SeleniumBaseTest extends BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void preCondition() {
        driver = DriverWrapper.getDriver();
        driver.navigate().to("https://www.booking.com");
        driver.manage().window().maximize();
//        Helper.implicitWait(Helper.PAGE_LOAD_TIME);
    }

    @AfterMethod
    public void postCondition() {
        driver.quit();
    }
}
