package com.nguyet.test.tool;

import com.nguyet.test.BaseTest;
import com.nguyet.test.core.DriverWrapper;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static com.nguyet.test.tool.Capturer.saveScreenShot;

public class Listener implements ITestListener {

    private final WebDriver driver = DriverWrapper.getDriver();

    @Override
    public void onStart(ITestContext context) {
        BaseTest.LOGGER.info("Test start");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Allure ScreenShot and SaveTestLog
        BaseTest.LOGGER.info("Test fail. Screenshot saved.");
        saveScreenShot(driver);

        DriverWrapper.reset();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        BaseTest.LOGGER.info("Success");

        DriverWrapper.reset();
    }

    @Override
    public void onFinish(ITestContext context) {
        BaseTest.LOGGER.info("Test finish");
    }
}
