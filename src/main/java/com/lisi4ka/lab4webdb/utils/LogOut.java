package com.lisi4ka.lab4webdb.utils;

import static com.lisi4ka.lab4webdb.utils.Check.tokenMap;

public class LogOut {
    public static void logOutHome(String token) {
        tokenMap.inverse().remove(Long.parseLong(token));
    }
}
