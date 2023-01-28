package com.jawwad.integrationtest.exceptionhandler.customexception;

import lombok.Data;

@Data
public class UserAlreadyExists extends RuntimeException{
    private String name;

    public UserAlreadyExists(String name) {
        super("User with " + name  +" already exists in system.");
    }
}
