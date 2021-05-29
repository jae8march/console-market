package com.company.app.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Class for validating input from the user.
 */
public class Validation {
    private static final String REGEX_INT = "(?<![-.])(\\b[0-9]+\\b)(?!\\.[0-9])";
    private static final String REGEX_EXTENSION = "\\.[a-zA-Z]+$";
    private static final String REGEX_DOUBLE = "(\\.\\d\\d|\\d+\\.\\d+|\\d+\\.)";

    private Validation(){}

    /**
     * Checking the entered value, the answer to the question.
     * @param string to check
     * @return true if string is 0 or 1
     */
    public static boolean isBinary(String string){
        return string.equals("0") || string.equals("1");
    }

    /**
     * Whether the entered value is an integer.
     * @param string to check
     * @return true if string is integer
     */
    public static boolean isInt(String string) {
        return string.matches(REGEX_INT);
    }

    /**
     * Whether the entered value is extension.
     * @param string to check
     * @return true if string is extension
     */
    public static boolean isExtension(String string) {
        return string.matches(REGEX_EXTENSION);
    }

    /**
     * Checks number integrity.
     * @param value input
     * @return true if value is double
     */
    public static boolean isDouble(String value) {
        return Pattern.compile(REGEX_DOUBLE).matcher(value).matches();
    }

    /**
     * Checks if string is a directory.
     * @param string to check
     * @return true if string is directory
     */
    public static boolean isDirectory(String string){
        File file = new File(string);
        return file.exists() && file.isDirectory();
    }

    /**
     * Parses text from a string to produce a Date.
     * @param date date string representation
     * @return Date representation of date, or null if time or date doesn't match the pattern
     */
    public static Date parseDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            return dateFormat.parse(date);
        } catch (Exception exception) {
            return null;
        }
    }
}
