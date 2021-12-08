package com.nguyet.test.booking.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateConverter {

    public static String convertYearMonth(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("LLLL yyyy");

        return date.format(formatter);
    }
}
