package com.example.itutor.main.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtilsHelper {
    private static final String SHORT_DATE_FORMAT = "yyyy-MM-dd";
    private static final String EMPTY = "";
    private static final String SIMPLE_TIME_FORMAT = "kk:mm";

    public static final SimpleDateFormat SHORT_SIMPLE_DATE_FORMAT = new SimpleDateFormat(SHORT_DATE_FORMAT);
    public static final SimpleDateFormat SIMPLE_DATE_TIME_FORMAT = new SimpleDateFormat(SIMPLE_TIME_FORMAT);

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

    public static String getSimpleFormattedTime(Date time) {
        return time == null ? EMPTY : SIMPLE_DATE_TIME_FORMAT.format(time);
    }

    public static Date getSimpleTime(String formattedTime) {
        try {
            return SIMPLE_DATE_TIME_FORMAT.parse(formattedTime);
        } catch (ParseException e) {
            return null;
        }
    }
}
