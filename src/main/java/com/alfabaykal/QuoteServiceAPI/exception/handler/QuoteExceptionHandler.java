package com.alfabaykal.QuoteServiceAPI.exception.handler;

import com.alfabaykal.QuoteServiceAPI.exception.QuoteNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class QuoteExceptionHandler {
    @ExceptionHandler(QuoteNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handleQuoteNotFound(QuoteNotFoundException e) {
        return e.getMessage();
    }
}
