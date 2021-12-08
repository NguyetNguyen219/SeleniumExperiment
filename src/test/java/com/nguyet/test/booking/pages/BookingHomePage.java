package com.nguyet.test.booking.pages;

import com.nguyet.test.BaseTest;
import com.nguyet.test.booking.utils.DateConverter;
import com.nguyet.test.core.DriverWrapper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.LocalDate;
import java.util.List;

public class BookingHomePage extends BookingBasePage {

    By searchTitleBy = By.xpath("//span[@class='sb-searchbox__title-text']");
    WebElement searchTitle;

    By destinationFieldBy = By.id("ss");
    WebElement destinationField;

    By checkinDateFieldBy = By.xpath("//div[@data-mode='checkin']");
    WebElement checkinDateField;

    By groupFieldBy = By.xpath("//*[@class='xp__input-group xp__guests']");
    WebElement groupField;

    By calendarPaneBy = By.className("bui-calendar");
    WebElement calendarPane;

    By calendarNextBtnBy = By.xpath("//div[@data-bui-ref='calendar-next']");
    WebElement calendarNextBtn;

    By searchBtnBy = By.xpath("//*[@class='sb-searchbox__button ']");
    WebElement searchBtn;

    By increaseAdultBtnBy = By.xpath("//button[@aria-label='Increase number of Adults']");
    By decreaseAdultBtnBy = By.xpath("//button[@aria-label='Decrease number of Adults']");
    By calendarMonthBy = By.className("bui-calendar__month");
    By groupCurrentNumbersBy = By.className("bui-stepper__display");
    List<WebElement> calendarMonths;
    List<WebElement> groupCurrentNumbers;

    /**
     * Constructor
     *
     * @param webDriver
     */
    public BookingHomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void waitForVisibilityOfCalendar() {
        wait.until(ExpectedConditions.presenceOfElementLocated(calendarPaneBy));
    }

    public void waitForVisibilityOfSearchTitle() {
        wait.until(ExpectedConditions.presenceOfElementLocated(searchTitleBy));
    }

    public void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public Integer getNumberOfAdult() {
        if (groupCurrentNumbers == null)
            return null;
        return Integer.parseInt(groupCurrentNumbers.get(0).getText());
    }

    /**
     * method send %destination% to the destination field
     *
     * @param destination - a string name of city or province
     * @return BookingHomePage .this
     */
    @Step("Set the destination as {destination}")
    public BookingHomePage setTextToDestinationField(String destination) {
        destinationField = driver.findElement(destinationFieldBy);
        scrollIntoView(destinationField);
        BaseTest.LOGGER.info("Send '" + destination + "' to the destination field");
        destinationField.sendKeys(destination);

        return this;
    }

    /**
     * method set number of adults in group field
     * using 2 buttons to increase and decrease number of adults
     *
     * @param adults - int type of number of adults
     * @return BookingHomePage .this
     */
    @Step("Set number of adults as {adults} in group field")
    public BookingHomePage setNumberOfAdultsInGroupField(int adults) {
        WebElement increaseBtn = driver.findElement(increaseAdultBtnBy);
        WebElement decreaseBtn = driver.findElement(decreaseAdultBtnBy);

        int adult_count;
        do {
            groupCurrentNumbers = driver.findElements(groupCurrentNumbersBy);
            adult_count = getNumberOfAdult();

            if (adult_count < adults) {
                increaseBtn.click();
            } else if (adult_count > adults) {
                decreaseBtn.click();
            }
        } while (adult_count != adults);

        return this;
    }

    /**
     * method click the search booking button
     * @return BookingSeachResultPage .this
     */
    @Step("Click search button")
    public BookingSeachResultPage clickSearchButton() {
        searchBtn = driver.findElement(searchBtnBy);
        searchBtn.click();

        return new BookingSeachResultPage(driver);
    }

    /**
     * method click the check-in date field to show up a calendar
     * @return BookingHomePage .this
     */
    @Step("Click check-in date field")
    public BookingHomePage clickCheckinDateField() {
        checkinDateField = driver.findElement(checkinDateFieldBy);
        BaseTest.LOGGER.info("Click the checkin date field");
        checkinDateField.click();

        return this;
    }

    @Step("Click group guest field")
    public BookingHomePage clickGroupGuestField() {
        groupField = driver.findElement(groupFieldBy);
        BaseTest.LOGGER.info("Click the guests group & room field");
        groupField.click();

        return this;
    }

    /**
     * method choose checkin date in the calendar
     *
     * @param date - a LocalDate variable represent the check-in date
     * @return BookingHomePage .this
     */
    @Step("Choose check-in date as {date}")
    public BookingHomePage chooseCheckinDate(LocalDate date) {
        BaseTest.LOGGER.info("Choose the checkin date as " + date);
        pickDayInCalendar(date);

        return this;
    }

    /**
     * method choose checkin and checkout date in the calendar
     *
     * @param checkinDate - a LocalDate variable represent the check-in date
     * @param checkoutDate - a LocalDate variable represent the check-out date
     * @return BookingHomePage .this
     */
    @Step("Choose check-out date as {checkoutDate}")
    public BookingHomePage chooseCheckinAndCheckoutDate(LocalDate checkinDate, LocalDate checkoutDate) {
        waitForVisibilityOfCalendar();
        calendarPane = driver.findElement(calendarPaneBy);

        chooseCheckinDate(checkinDate);

        BaseTest.LOGGER.info("Choose the checkout date as " + checkoutDate);
        pickDayInCalendar(checkoutDate);

        return this;
    }

    /**
     * method find and choose a date in the calendar
     *
     * @param date - LocalDate type of the date want to choose
     */
    private void pickDayInCalendar(LocalDate date) {
        String yearMonth = DateConverter.convertYearMonth(date);
        calendarNextBtn = driver.findElement(calendarNextBtnBy);

        boolean hasMonth;
        do {
            calendarMonths = driver.findElements(calendarMonthBy);
            hasMonth = calendarMonths
                    .stream()
                    .anyMatch(e -> e.getText().contains(yearMonth));
            // If the month hasn't showed, click next
            if (!hasMonth) {
                calendarNextBtn.click();
            }
        } while (!hasMonth);

        // Choose the date in the calendar
        driver.findElement(By.xpath("//span[@aria-label='" +
                        date.getDayOfMonth() + " " + yearMonth + "']")).click();
    }
}
