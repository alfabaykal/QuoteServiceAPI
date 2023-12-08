package com.alfabaykal.QuoteServiceAPI.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException() {
        super("User with this email already exist");
    }
}
