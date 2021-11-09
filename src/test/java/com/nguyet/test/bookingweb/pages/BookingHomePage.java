package com.nguyet.test.bookingweb.pages;

import com.nguyet.test.BaseTest;
import com.nguyet.test.bookingweb.utils.DateConverter;
import com.nguyet.test.core.DriverWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.time.LocalDate;
import java.util.List;

public class BookingHomePage extends BookingBasePage {

    @FindBy(className = "sb-searchbox__title-text")
    WebElement searchTitle;

    @FindBy(id = "ss")
    WebElement destinationField;

    @FindBy(xpath = "//div[@data-mode='checkin']")
    WebElement checkinDateField;

    @FindBy(xpath = "//*[@class='xp__input-group xp__guests']")
    WebElement groupField;

    @FindBy(className = "bui-calendar")
    WebElement calendarPane;

    @FindBy(id = "xp__guests__inputs-container")
    WebElement groupInputPane;

    @FindBy(xpath = "//div[@data-bui-ref='calendar-next']")
    WebElement calendarNextBtn;

    @FindBy(xpath = "//*[@class='sb-searchbox__button ']")
    WebElement searchBtn;

    By increaseAdultBtnBy = By.xpath("//button[@aria-label='Increase number of Adults']");
    By decreaseAdultBtnBy = By.xpath("//button[@aria-label='Decrease number of Adults']");
    By calendarMonthBy = By.className("bui-calendar__month");
    By groupCurrentNumbersBy = By.className("bui-stepper__display");
    List<WebElement> calendarMonths;
    List<WebElement> groupCurrentNumbers;

    public void waitForVisibilityOfCalendar() {
        wait.until(ExpectedConditions.visibilityOf(calendarPane));
    }

    public String getSearchTitle() {
        return searchTitle.getText();
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
    public BookingHomePage setTextToDestinationField(String destination) {
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
    public BookingHomePage setNumberOfAdultsInGroupField(int adults) {
        Assert.assertTrue(groupInputPane.isDisplayed());

        WebElement increaseBtn = DriverWrapper.getDriver().findElement(increaseAdultBtnBy);
        WebElement decreaseBtn = DriverWrapper.getDriver().findElement(decreaseAdultBtnBy);

        int adult_count;
        do {
            groupCurrentNumbers = DriverWrapper.getDriver().findElements(groupCurrentNumbersBy);
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
    public BookingSeachResultPage clickSearchButton() {
        searchBtn.click();

        return new BookingSeachResultPage();
    }

    /**
     * method click the check-in date field to show up a calendar
     * @return BookingHomePage .this
     */
    public BookingHomePage clickCheckinDateField() {
        BaseTest.LOGGER.info("Click the checkin date field");
        checkinDateField.click();

        return this;
    }

    public BookingHomePage clickGroupGuestField() {
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
    public BookingHomePage chooseCheckinDate(LocalDate date) {
        Assert.assertFalse(date.isBefore(LocalDate.now()));
        waitForVisibilityOfCalendar();

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
    public BookingHomePage chooseCheckinAndCheckoutDate(LocalDate checkinDate, LocalDate checkoutDate) {
        Assert.assertTrue(checkoutDate.isAfter(checkinDate));

        waitForVisibilityOfCalendar();
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
        Assert.assertTrue(calendarPane.isDisplayed());

        String yearMonth = DateConverter.convertYearMonth(date);

        boolean hasMonth;
        do {
            calendarMonths = DriverWrapper.getDriver().findElements(calendarMonthBy);
            hasMonth = calendarMonths
                    .stream()
                    .anyMatch(e -> e.getText().contains(yearMonth));
            // If the month hasn't showed, click next
            if (!hasMonth) {
                calendarNextBtn.click();
            }
        } while (!hasMonth);
        // Choose the date in the calendar
        WebElement day = DriverWrapper.getDriver()
                .findElement(By.xpath("//span[@aria-label='" +
                        date.getDayOfMonth() + " " +
                        yearMonth + "']"));
        day.click();
    }
}
