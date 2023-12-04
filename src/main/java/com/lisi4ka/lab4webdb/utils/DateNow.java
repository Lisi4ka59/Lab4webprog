package com.lisi4ka.lab4webdb.utils;

import java.time.LocalDateTime;

public class DateNow {
    public static String dateTimeNow() {
        StringBuilder result = new StringBuilder();
        int hour = LocalDateTime.now().getHour();
        int minute = LocalDateTime.now().getMinute();
        int second = LocalDateTime.now().getSecond();
        result.append((hour < 10) ? "0" + hour : hour);
        result.append(":");
        result.append(((minute < 10) ? "0" + minute : minute));
        result.append(":");
        result.append((second < 10) ? "0" + second : second);
        result.append(" ");
        result.append(LocalDateTime.now().getDayOfWeek().toString());
        result.append(" ");
        result.append(LocalDateTime.now().getDayOfMonth());
        result.append("/");
        result.append(LocalDateTime.now().getMonth().toString());
        result.append("/");
        result.append(LocalDateTime.now().getYear());
        return result.toString();
    }
}
