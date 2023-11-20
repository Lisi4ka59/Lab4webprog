package com.lisi4ka.lab4webdb.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;


public abstract class Check {
    public static HashMap<String, Long> tokenMap = new HashMap<>();
    public static String checkToken(long token) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(tokenMap.containsValue(token));
    }
}
