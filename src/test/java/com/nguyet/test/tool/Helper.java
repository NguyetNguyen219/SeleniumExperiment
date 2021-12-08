package com.nguyet.test.tool;

import com.nguyet.test.core.DriverWrapper;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

/**
 * A tool class that contains time variables and wait methods
 * help to write Selenium test cases
 */
public final class Helper {

//    private static WebDriver driver = DriverWrapper.getDriver();

    /**
     * variable type Long
     * represent the average wait time (time unit)
     */
    public static long AVERAGE_TIME_WAIT = 8;
    /**
     * variable type Long
     * represent the time wait for visibility of elements (time unit)
     */
    public static long ELEMENT_VISIBILITY_TIME = 5;
    /**
     * variable type Long
     * represent the time wait for elements to be clickable (time unit)
     */
    public static long ELEMENT_CLICKABLE_TIME = 8;
    /**
     * variable type Long
     * represent the time wait for loading page (time unit)
     */
    public static long PAGE_LOAD_TIME = 10;
}
