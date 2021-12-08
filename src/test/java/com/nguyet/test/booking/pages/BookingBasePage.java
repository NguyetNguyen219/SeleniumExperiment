package com.nguyet.test.booking.pages;

import com.nguyet.test.BaseTest;
import com.nguyet.test.core.DriverWrapper;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.nguyet.test.tool.Helper.AVERAGE_TIME_WAIT;

public class BookingBasePage {

    protected WebDriver driver;

    @FindBy(xpath = "//img[@class ='bui-avatar__image']")
    protected WebElement languageBtn;

    @FindBy(xpath = "//a[@data-lang='en-us']")
    protected WebElement usEnglishBtn;

    @FindBy(xpath = "//div[@aria-label='Select your language']")
    protected WebElement languagePanel;

    protected final WebDriverWait wait;

    /**
     * Constructor
     */
    public BookingBasePage(WebDriver webDriver) {
        driver = webDriver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(AVERAGE_TIME_WAIT));
        PageFactory.initElements(driver, this);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    @Step("Click the language button in menu")
    public BookingBasePage clickLanguageButton() {
        BaseTest.LOGGER.info("CLick to select the page's language");
        languageBtn.click();

        return this;
    }

    @Step("Choose English-US in list of languages")
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
