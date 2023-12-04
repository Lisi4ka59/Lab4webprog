package com.lisi4ka.lab4webdb.utils;

import com.lisi4ka.lab4webdb.db.RegUser;
import com.lisi4ka.lab4webdb.db.RegUserRepository;

import java.util.Optional;

import static com.lisi4ka.lab4webdb.utils.Check.tokenMap;


public abstract class SignIn {

    public static boolean signIn(String login, String password, long token, RegUserRepository regUserRepository) {
        String hashedPassword = PasswordManager.hashedPassword(password);
        Optional<RegUser> regUser = regUserRepository.findByUsername(login);
        boolean isSign = regUser.isPresent() && regUser.get().getPassword().equals(hashedPassword);
        if (isSign) {
            tokenMap.remove(login);
            tokenMap.put(login, token);
        }
        return isSign;
    }
}
