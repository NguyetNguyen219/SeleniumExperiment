package com.nguyet.test.selenium4test;

import com.nguyet.test.bookingweb.pages.BookingBasePage;
import com.nguyet.test.bookingweb.pages.BookingHomePage;
import com.nguyet.test.bookingweb.pages.BookingSeachResultPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.time.Month;

public class SearchBookingPageTest extends SeleniumBaseTest {

    private String destination = "Paris";

    /**
     * method set up the page's language is English as default
     * @param page - BookingBasePage as the base of every pages
     */
    public void setupLanguage(BookingBasePage page) {
        page.clickLanguageButton()
                .clickUSEnglishButton();
    }

    @Test(description = "Test search a place in Paris 13-15 February 2022 for 2 adults")
    public void testSearchBookingResult() {
        LOGGER.info("Start the test");
        SoftAssert softAssert = new SoftAssert();

        LocalDate checkin = LocalDate.of(2022, Month.FEBRUARY, 13);
        LocalDate checkout = LocalDate.of(2022, Month.FEBRUARY, 15);

        BookingHomePage homepage = new BookingHomePage();
        softAssert.assertTrue(homepage.getPageTitle().contains("Booking.com"));

        setupLanguage(homepage);
        softAssert.assertEquals(homepage.getSearchTitle(),
                "Find deals on hotels, homes, and much more...");

        BookingSeachResultPage resultPage = homepage.setTextToDestinationField(destination)
                .clickCheckinDateField()
                .chooseCheckinAndCheckoutDate(checkin, checkout)
                .clickGroupGuestField()
                .setNumberOfAdultsInGroupField(2)
                .clickSearchButton();

        resultPage.waitForVisibilityOfResultTitle();
        softAssert.assertEquals(resultPage.getCurrentDestination(), destination);

        LOGGER.info("End test");
        softAssert.assertAll();
    }
}
