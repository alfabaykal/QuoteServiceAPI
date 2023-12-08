package com.alfabaykal.QuoteServiceAPI.exception;

public class QuoteNotFoundException extends RuntimeException {
    public QuoteNotFoundException(Long id) {
        super("Quote with id " + id + " not found");
    }

    public QuoteNotFoundException() {
        super("There are no quotes in DB");
    }
}
