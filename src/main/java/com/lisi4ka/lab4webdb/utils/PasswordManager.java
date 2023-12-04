package com.lisi4ka.lab4webdb.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class PasswordManager {

    public static String hashedPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());

            // Преобразование массива байт в шестнадцатеричное представление
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : hashedBytes) {
                stringBuilder.append(String.format("%02x", b));
            }

            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}
