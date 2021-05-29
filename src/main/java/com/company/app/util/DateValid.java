package com.company.app.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class for work with date.
 */
public class DateValid {
    private DateValid(){}

    /**
     * Parses text from a string to produce a Date.
     * @param date date string representation
     * @param time string representation of time
     * @return Date representation of time and date, or null if time or date doesn't match the pattern
     */
    public static Date parseDate(String date, String time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        try {
            return dateFormat.parse(date + " " + time);
        } catch (Exception exception) {
            return null;
        }
    }
}
