/*
 * Copyright (c) 2018 Total SoftTech Solutions, Inc. All rights reserved. All other trademarks and copyrights referred to herein are the property of their respective holders. No part of this code may be reproduced in any form or by any means or used to take any derivative work, without written permission from Total SoftTech Solutions, Inc.
 */

package com.cpu.utils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.TimeZone;

public class DateTimeUtil {

    public static final SimpleDateFormat MM_DD_YY = new SimpleDateFormat("MM/dd/yy");
    public static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("MM-dd-yy hh:mm:ss a");
    public static final SimpleDateFormat MDDYY = new SimpleDateFormat("MMddyy");

    public DateTimeUtil() {}


    public static LocalDate longToLocalDate(Long date) {
        if (date == null || date == 0) {
            return null;
        }
        return Instant.ofEpochMilli(date).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static String dateLongToString(Long date, String format) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat(format).format(date);
    }

    public static long getStartOfMonth(long millis){
        Calendar date = Calendar.getInstance();
        date.setTimeInMillis(millis);
        date.set(Calendar.HOUR_OF_DAY, date.getActualMinimum(Calendar.HOUR_OF_DAY));
        date.set(Calendar.MINUTE, date.getActualMinimum(Calendar.MINUTE));
        date.set(Calendar.SECOND, date.getActualMinimum(Calendar.SECOND));
        date.set(Calendar.DATE, date.getActualMinimum(Calendar.DATE));
        return date.getTimeInMillis();
    }

    public static long getEndOfMonth(long millis){
        Calendar date = Calendar.getInstance();
        date.setTimeInMillis(millis);
        date.set(Calendar.HOUR_OF_DAY, date.getActualMaximum(Calendar.HOUR_OF_DAY));
        date.set(Calendar.MINUTE, date.getActualMaximum(Calendar.MINUTE));
        date.set(Calendar.SECOND, date.getActualMaximum(Calendar.SECOND));
        date.set(Calendar.DATE, date.getActualMaximum(Calendar.DATE));
        return date.getTimeInMillis();
    }




    public static String getMillisInDateSeparatedString(Long timeMillis) {
        if (null == timeMillis) {
            return null;
        }
        return MM_DD_YY.format(timeMillis);
    }

    public static String getMillisInDateString(long timeMillis) {
        return MDDYY.format(timeMillis);
    }

    public static String getDateRangeString(Long dateFrom, Long dateTo) {
        if (null == dateFrom || dateFrom == 0) {
            return null;
        }
        String from = MM_DD_YY.format(dateFrom);
        String to = dateTo == null ? "" : MM_DD_YY.format(dateTo);
        return from + " - " + to;
    }

    public static boolean isSameDate(long date1, long date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTimeInMillis(date2);

        return cal1.get(Calendar.DATE) == cal2.get(Calendar.DATE)
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)
                && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
    }

    public static boolean after(Long now, Long date) {
        if (null == now) {
            return true;
        }
        Calendar nowCalendar = Calendar.getInstance();
        nowCalendar.setTimeInMillis(now);
        nowCalendar.set(Calendar.HOUR_OF_DAY, nowCalendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        nowCalendar.set(Calendar.MINUTE, nowCalendar.getActualMinimum(Calendar.MINUTE));
        nowCalendar.set(Calendar.SECOND, nowCalendar.getActualMinimum(Calendar.SECOND));

        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTimeInMillis(date);
        dateCalendar.set(Calendar.HOUR_OF_DAY, dateCalendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        dateCalendar.set(Calendar.MINUTE, dateCalendar.getActualMinimum(Calendar.MINUTE));
        dateCalendar.set(Calendar.SECOND, dateCalendar.getActualMinimum(Calendar.SECOND));

        return nowCalendar.after(dateCalendar);
    }

    public static String toFromServerDateTimeText(long timeStamp) {
        return DATE_TIME_FORMAT.format(timeStamp - TimeZone.getDefault().getRawOffset());
    }

    public static Long getDate(Long date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date);
        cal.add(Calendar.DATE, days);
        return cal.getTimeInMillis();
    }

}
