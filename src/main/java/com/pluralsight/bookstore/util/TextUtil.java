package com.pluralsight.bookstore.util;

/**
 * Text util class.
 */
public class TextUtil {

    /**
     * Sanitize string by removing any double spaces from a String.
     *
     * @param textToSanitize the text to sanitize
     * @return the string
     */
    public String sanitize(String textToSanitize) {
        return textToSanitize.replaceAll("\\s+", " ");
    }
}
