package com.alfabaykal.QuoteServiceAPI.exception.handler;

import com.alfabaykal.QuoteServiceAPI.exception.EmailAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RegistrationExceptionHandler {
    @ExceptionHandler(EmailAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleQuoteNotFound(EmailAlreadyExistsException e) {
        return e.getMessage();
    }
}
