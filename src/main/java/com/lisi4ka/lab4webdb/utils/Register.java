package com.lisi4ka.lab4webdb.utils;

import com.lisi4ka.lab4webdb.db.RegUser;
import com.lisi4ka.lab4webdb.db.RegUserRepository;

import java.util.Optional;


public abstract class Register {
    public static boolean registration(String login, String password, RegUserRepository regUserRepository) {
        String hashedPassword = PasswordManager.hashedPassword(password);
        Optional<RegUser> regUser = regUserRepository.findByUsername(login);
        if (regUser.isPresent()) {
            return false;
        }
        RegUser regUserNew = new RegUser();
        regUserNew.setUsername(login);
        regUserNew.setPassword(hashedPassword);
        regUserRepository.save(regUserNew);
        return true;
    }
}
