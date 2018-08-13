package com.jc.cluz.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    /**
     * get time stamp, current time millis
     * @return
     */
    public static String getTimeStamp(){
        return String.valueOf(System.currentTimeMillis());
    }

    public static String getmmss(){
        Date curTime = new Date();
        SimpleDateFormat time=new SimpleDateFormat("mmss");
        return time.format(curTime).toString();
    }
}
