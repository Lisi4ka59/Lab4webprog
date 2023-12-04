package com.lisi4ka.lab4webdb.utils;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;



public abstract class Check {

    public static BiMap<String, Long> tokenMap = HashBiMap.create();
    public static boolean checkToken(String token) {
        long parseToken = 0;
        try {
            parseToken = Long.parseLong(token);
        } catch (NumberFormatException ignored) {}
        return tokenMap.containsValue(parseToken);
    }
}
