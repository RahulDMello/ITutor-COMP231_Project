package com.example.itutor.main.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtilsHelper {
    private static final String SHORT_DATE_FORMAT = "yyyy-MM-dd";
    private static final String EMPTY = "";

    public static final SimpleDateFormat SHORT_SIMPLE_DATE_FORMAT = new SimpleDateFormat(SHORT_DATE_FORMAT);

    public static String getShortFormattedDate(Date date) {
        return date == null ? EMPTY : SHORT_SIMPLE_DATE_FORMAT.format(date);
    }

    public static Date getShortDate(String formattedDateOfBirth) {
        try {
            return SHORT_SIMPLE_DATE_FORMAT.parse(formattedDateOfBirth);
        } catch (ParseException e) {
            return null;
        }
    }
}
