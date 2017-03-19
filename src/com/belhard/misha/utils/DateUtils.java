package com.belhard.misha.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public final class DateUtils {

    private DateUtils() {
        throw new InstantiationError("Class contains static methods only. You should not instantiate it!");
    }

    public static final String DATE_TIME_FORMAT = "dd-MM-yyyy_HH-mm-ss";

    public static String getCurrentDateTimeAsStr() {
        long currentTime = System.currentTimeMillis();
        Date date = new Date(currentTime);

        DateFormat formatter = new SimpleDateFormat(DATE_TIME_FORMAT);
        String strDate = formatter.format(date);

        return strDate;
    }
}
