package com.lisi4ka.lab4webdb.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.HashMap;


public abstract class CurrentTime {
    public static String jsonTimeNow() throws JsonProcessingException {
        HashMap<String, String> dateMap = new HashMap<>();
        dateMap.put("year", String.valueOf(LocalDateTime.now().getYear()));
        dateMap.put("month", LocalDateTime.now().getMonth().toString());
        dateMap.put("day", String.valueOf(LocalDateTime.now().getDayOfMonth()));
        dateMap.put("day_of_week", LocalDateTime.now().getDayOfWeek().toString());
        int hour = LocalDateTime.now().getHour();
        dateMap.put("hour", String.valueOf((hour < 10)? "0" + hour : hour));
        int minute = LocalDateTime.now().getMinute();
        dateMap.put("minute", (String.valueOf((minute < 10)? "0" + minute : minute)));
        int second = LocalDateTime.now().getSecond();
        dateMap.put("second", String.valueOf((second < 10)? "0" + second : second));
        ObjectMapper objectMapper = new ObjectMapper();
        return "[" + objectMapper.writeValueAsString(dateMap) + "]";
    }
}
