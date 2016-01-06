/**
 * This document and its contents are protected by copyright 2005 and owned by 59store.com Inc. The
 * copying and reproduction of this document and/or its content (whether wholly or partly) or any
 * incorporation of the same into any other material in any media or format of any kind is strictly
 * prohibited. All rights are reserved.
 * <p>
 * Copyright (c) 59store.com Inc. 2015
 */
package com.store59.box;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by shanren on 15/8/27.
 */
public class TimeTest {

    public static void main(String[] args) {
//        System.setProperty("user.timezone", "GMT+0");
//        TimeZone.setDefault(TimeZone.getTimeZone("GMT+0"));
//
//        System.out.println(System.currentTimeMillis());
//
//        System.out.println("*****");
        Calendar calendar = Calendar.getInstance();
//
////        System.out.println(calendar.getTime());
//        System.out.println(calendar.getTimeInMillis());
//        System.out.println(calendar.getTimeZone().getDisplayName());
//        calendar.set(Calendar.HOUR, 0);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        System.out.println(calendar.getTimeZone().getDisplayName());
////        System.out.println(calendar.getTime());
//        System.out.println(calendar.getTimeInMillis());
//
//        System.setProperty("user.timezone", "GMT+8");
//        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
//        calendar = Calendar.getInstance();

//        System.out.println(calendar.getTime());
        System.out.println(calendar.getTimeInMillis());
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        System.out.println(calendar.getTimeZone().getDisplayName());
//        System.out.println(calendar.getTime());
        System.out.println(calendar.getTimeInMillis());



    }

}
