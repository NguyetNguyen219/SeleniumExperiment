package com.nguyet.test.selenium4test;

import com.nguyet.test.booking.pages.BookingBasePage;
import com.nguyet.test.booking.pages.BookingHomePage;
import com.nguyet.test.booking.pages.BookingSeachResultPage;
import com.nguyet.test.data.Data;
import com.nguyet.test.tool.Listener;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;

@Listeners(Listener.class)
public class SearchBookingPageTest extends SeleniumBaseTest {

    /**
     * method set up the page's language is English as default
     *
     * @param page - BookingBasePage as the base of every pages
     */
    public void setupLanguage(BookingBasePage page) {
        page.clickLanguageButton()
                .clickUSEnglishButton();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test search a place in Paris 13-15 February 2022 for 2 adults")
    @Test(description = "TC-1 ::: Search booking place",
            dataProvider = "book-info",
            dataProviderClass = Data.class)
    public void testSearchBooking(String destination, LocalDate checkin,
                                  LocalDate checkout, int numberOfAdult) {
        SoftAssert softAssert = new SoftAssert();

        BookingHomePage homepage = new BookingHomePage(driver);
        softAssert.assertTrue(homepage.getPageTitle().contains("Booking.com"));

        setupLanguage(homepage);

        BookingSeachResultPage resultPage = homepage.setTextToDestinationField(destination)
                .clickCheckinDateField()
                .chooseCheckinAndCheckoutDate(checkin, checkout)
                .clickGroupGuestField()
                .setNumberOfAdultsInGroupField(numberOfAdult)
                .clickSearchButton();

        resultPage.waitForVisibilityOfResultTitle();
        softAssert.assertEquals(resultPage.getCurrentDestination(), destination);

        softAssert.assertAll();
    }
}
