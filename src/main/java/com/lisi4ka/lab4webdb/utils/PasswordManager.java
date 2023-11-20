package com.lisi4ka.lab4webdb.utils;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public abstract class PasswordManager {
    static Argon2 argon2 = Argon2Factory.create();

    public static String hashedPassword(String password) {
        String hashedPassword = argon2.hash(10, 65536, 1, password); // параметры: время, память, потоки, пароль
        argon2.wipeArray(password.toCharArray());
        return hashedPassword;
    }
}
