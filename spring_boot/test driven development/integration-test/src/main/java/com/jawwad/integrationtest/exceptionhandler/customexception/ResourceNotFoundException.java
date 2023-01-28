package com.jawwad.integrationtest.exceptionhandler.customexception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}