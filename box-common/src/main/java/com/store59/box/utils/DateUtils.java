/**
 * Copyright (c) 2015, 59store. All rights reserved.
 */
package com.store59.box.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author <a href="mailto:zhangwangy@59store.com">阿勇</a>
 * @version 1.0 15/12/28
 * @since 1.0
 */
public class DateUtils {

    public static Long getStartTime(){
        Calendar todayStart = new GregorianCalendar();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime().getTime()/1000;
    }

    public static Long getEndTime(){
        Calendar todayEnd = new GregorianCalendar();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 0);
        return todayEnd.getTime().getTime()/1000;
    }

}
