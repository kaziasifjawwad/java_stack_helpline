package com.jawwad.integrationtest.exceptionhandler.customexception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BaseException extends Exception {
    protected String message;
    protected String customMessage;
    protected String code;
    protected HttpStatus httpStatus;

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
        this.message = message;
    }


}
