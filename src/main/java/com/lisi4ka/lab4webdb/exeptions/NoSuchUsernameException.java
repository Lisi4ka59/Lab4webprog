package com.lisi4ka.lab4webdb.exeptions;

public class NoSuchUsernameException extends RuntimeException{
    @Override
    public String getMessage() {
        return "There is no user with that ID!";
    }
}
