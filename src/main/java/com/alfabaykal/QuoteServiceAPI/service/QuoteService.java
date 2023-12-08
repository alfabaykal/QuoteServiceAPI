package com.alfabaykal.QuoteServiceAPI.service;

import com.alfabaykal.QuoteServiceAPI.model.Quote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuoteService {
    Quote saveQuote(Quote quote);

    Quote getQuoteById(Long quoteId);

    Page<Quote> getTopRatedQuotes(Pageable pageable);

    Page<Quote> getLowestRatedQuotes(Pageable pageable);

    Quote getRandomQuote();

    Page<Quote> getLastQuotes(Pageable pageable);

    Page<Quote> getLastVotedQuotes(Pageable pageable);

    Quote updateQuote(Long quoteId, Quote quote);

    void deleteQuote(Long quoteId);

    List<Quote> getAllByUserId(Long id);
}
