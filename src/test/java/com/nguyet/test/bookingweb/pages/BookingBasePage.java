package com.nguyet.test.bookingweb.pages;

import com.nguyet.test.BaseTest;
import com.nguyet.test.core.DriverWrapper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookingBasePage {

    @FindBy(xpath = "//img[@class ='bui-avatar__image']")
    protected WebElement languageBtn;

    @FindBy(xpath = "//a[@data-lang='en-us']")
    protected WebElement usEnglishBtn;

    @FindBy(xpath = "//div[@aria-label='Select your language']")
    protected WebElement languagePanel;

    protected final WebDriverWait wait = DriverWrapper.getDriverWait();

    public BookingBasePage() {
        PageFactory.initElements(DriverWrapper.getDriver(), this);
    }

    public String getPageTitle() {
        return DriverWrapper.getDriver().getTitle();
    }

    public BookingBasePage clickLanguageButton() {
        BaseTest.LOGGER.info("CLick to select the page's language");
        languageBtn.click();

        return this;
    }

    public void clickUSEnglishButton() {
        waitForVisibilityOfLanguagePanel();

        BaseTest.LOGGER.info("Select language eng-us");
        usEnglishBtn.click();

        waitForInvisibilityOfLanguagePanel();
    }

    public void waitForVisibilityOfLanguageBtn() {
        wait.until(ExpectedConditions.visibilityOf(languageBtn));
    }

    public void waitForVisibilityOfLanguagePanel() {
        wait.until(ExpectedConditions.visibilityOf(languagePanel));
    }

    public void waitForInvisibilityOfLanguagePanel() {
        wait.until(ExpectedConditions.invisibilityOf(languagePanel));
    }
}
