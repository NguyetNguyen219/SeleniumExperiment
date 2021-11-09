package com.nguyet.test.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.nguyet.test.tool.Helper.AVERAGE_TIME_WAIT;

public class DriverWrapper {
    private static WebDriver driver = null;
    private static WebDriverWait driverWait = null;

    public DriverWrapper() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--lang=es");

        ChromeDriverService service = ChromeDriverService.createServiceWithConfig(options);

        driver = new ChromeDriver(service);
    }

    public static WebDriver getDriver() {
        if(driver == null)
            new DriverWrapper();
        return driver;
    }

    public static WebDriverWait getDriverWait() {
        if(driver == null)
            new DriverWrapper();
        driverWait = new WebDriverWait(driver, Duration.ofSeconds(AVERAGE_TIME_WAIT));
        return driverWait;
    }
}
