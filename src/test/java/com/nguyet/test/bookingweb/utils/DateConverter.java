package com.nguyet.test.bookingweb.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateConverter {

    private static DateTimeFormatter formatter;

    public static String convertYearMonth(LocalDate date) {
        formatter = DateTimeFormatter.ofPattern("LLLL yyyy");

        return date.format(formatter);
    }
}
