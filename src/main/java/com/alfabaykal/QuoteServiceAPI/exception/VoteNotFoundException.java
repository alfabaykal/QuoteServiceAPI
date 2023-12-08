package com.alfabaykal.QuoteServiceAPI.exception;

public class VoteNotFoundException extends RuntimeException {
    public VoteNotFoundException() {
        super("Vote not found");
    }

    public VoteNotFoundException(Long voteId) {
        super("Vote with id " + voteId + " not found");
    }
}
