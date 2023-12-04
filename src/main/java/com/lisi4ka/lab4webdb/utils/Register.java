package com.lisi4ka.lab4webdb.utils;

import com.lisi4ka.lab4webdb.db.RegUser;
import com.lisi4ka.lab4webdb.db.RegUserRepository;

import java.util.Optional;

import static com.lisi4ka.lab4webdb.utils.Check.tokenMap;


public abstract class Register {
    public static String registration(String login, String password, String repeatPassword, long token, RegUserRepository regUserRepository) {
        if (login.isEmpty()) {
            return "no_login";
        }
        if (password.length() < 8) {
            return "short_password";
        }
        if (!password.equals(repeatPassword)) {
            return "mismatch_passwords";
        }
        String hashedPassword = PasswordManager.hashedPassword(password);
        Optional<RegUser> regUser = regUserRepository.findByUsername(login);
        if (regUser.isPresent()) {
            return "false";
        }
        RegUser regUserNew = new RegUser();
        regUserNew.setUsername(login);
        regUserNew.setPassword(hashedPassword);
        regUserRepository.save(regUserNew);
        tokenMap.remove(login);
        tokenMap.put(login, token);
        return "true";
    }
}
