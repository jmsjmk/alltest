package com.jiamingku.thead.threadpool.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("all")
public class DateUtils {

    public static String defaultMethod(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }


    public static String defaultMethodNow() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }
}
