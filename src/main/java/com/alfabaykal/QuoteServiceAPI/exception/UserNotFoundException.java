package com.alfabaykal.QuoteServiceAPI.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("User with id " + id + " not found");
    }

    public UserNotFoundException(String email) {
        super("User with login " + email + " not found");
    }
}
