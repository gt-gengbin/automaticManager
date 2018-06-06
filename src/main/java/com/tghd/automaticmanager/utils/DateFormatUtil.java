package com.tghd.automaticmanager.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {

    public static final String DATA_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String DATA_NOT_TIME = "yyyy-MM-dd";

    public static String nowDateToString(String formatstr){
        SimpleDateFormat format = new SimpleDateFormat(formatstr);
        return format.format(new Date());
    }

    public static String dateToString(Date date,String formatstr){
        SimpleDateFormat format = new SimpleDateFormat(formatstr);
        return format.format(date);
    }

    public static String nowDateTimeToString(String formatstr){
        SimpleDateFormat format = new SimpleDateFormat(formatstr);
        return format.format(new Date());
    }
}
