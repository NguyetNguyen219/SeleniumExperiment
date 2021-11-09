package com.nguyet.test.bookingweb.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BookingSeachResultPage extends BookingBasePage {

    @FindBy(id = "ss")
    WebElement destinationField;

    @FindBy(xpath = "//*[@class='_30227359d _0db903e42']")
    WebElement resultTitle;

    public void waitForVisibilityOfResultTitle() {
        wait.until(ExpectedConditions.visibilityOf(resultTitle));
    }

    public String getCurrentDestination() {
        return destinationField.getAttribute("value");
    }
}
