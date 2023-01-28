package com.jawwad.integrationtest.exceptionhandler;


import com.jawwad.integrationtest.exceptionhandler.customexception.ResourceNotFoundException;
import com.jawwad.integrationtest.exceptionhandler.customexception.UserAlreadyExists;
import com.jawwad.integrationtest.exceptionhandler.exceptionresponse.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage resourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        return new ErrorMessage(
                ex.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage globalExceptionHandler(Exception ex, HttpServletRequest request) {
        return new ErrorMessage(
                ex.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(UserAlreadyExists.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage userAlreadyExistHandler(UserAlreadyExists ex, HttpServletRequest request) {
        return new ErrorMessage(
                ex.getMessage(),
                request.getRequestURI()
        );
    }
}