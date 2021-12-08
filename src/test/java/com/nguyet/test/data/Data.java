package com.nguyet.test.data;

import org.testng.annotations.DataProvider;

import java.time.LocalDate;
import java.time.Month;

public class Data {

    @DataProvider(name = "book-info")
    public static Object[][] bookingInfo() {
        return new Object[][]{
                {
                        "Paris",
                        LocalDate.of(2022, Month.FEBRUARY, 10),
                        LocalDate.of(2022, Month.FEBRUARY, 15),
                        2
                },
                {
                        "Tokyo",
                        LocalDate.of(2022, Month.APRIL, 20),
                        LocalDate.of(2022, Month.MAY, 5),
                        2
                }
        };
    }
}
