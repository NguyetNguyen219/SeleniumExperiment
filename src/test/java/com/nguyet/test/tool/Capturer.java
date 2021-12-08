package com.nguyet.test.tool;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;

public class Capturer {

    @Attachment(fileExtension = ".png")
    public static byte[] saveScreenShot(WebDriver driver) {
        Allure.addAttachment(LocalDate.now() + ".png",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }
}