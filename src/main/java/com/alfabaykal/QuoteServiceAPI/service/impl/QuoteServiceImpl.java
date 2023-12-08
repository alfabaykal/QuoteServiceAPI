package com.alfabaykal.QuoteServiceAPI.service.impl;

import com.alfabaykal.QuoteServiceAPI.exception.QuoteNotFoundException;
import com.alfabaykal.QuoteServiceAPI.model.Quote;
import com.alfabaykal.QuoteServiceAPI.repository.QuoteRepository;
import com.alfabaykal.QuoteServiceAPI.repository.VoteRepository;
import com.alfabaykal.QuoteServiceAPI.service.QuoteService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class QuoteServiceImpl implements QuoteService {
    private final QuoteRepository quoteRepository;
    private final VoteRepository voteRepository;

    public QuoteServiceImpl(QuoteRepository quoteRepository, VoteRepository voteRepository) {
        this.quoteRepository = quoteRepository;
        this.voteRepository = voteRepository;
    }

    @Override
    public Quote getQuoteById(Long quoteId) {
        return quoteRepository.findById(quoteId)
                .orElseThrow(() -> new QuoteNotFoundException(quoteId));
    }

    @Override
    public Quote saveQuote(Quote quote) {
        return quoteRepository.save(quote);
    }

    @Override
    public Page<Quote> getTopRatedQuotes(Pageable pageable) {
        return quoteRepository.findAllByOrderByVotesDesc(pageable);
    }

    @Override
    public Page<Quote> getLowestRatedQuotes(Pageable pageable) {
        return quoteRepository.findAllByOrderByVotes(pageable);
    }

    @Override
    public Quote getRandomQuote() {
        return quoteRepository.findRandomQuote()
                .orElseThrow(QuoteNotFoundException::new);
    }

    @Override
    public Page<Quote> getLastQuotes(Pageable pageable) {
        return quoteRepository.findAllByOrderByCreatedDesc(pageable);
    }

    @Override
    public Page<Quote> getLastVotedQuotes(Pageable pageable) {
        return quoteRepository.findAllByOrderByLastVoteDateDesc(pageable);
    }

    @Override
    public Quote updateQuote(Long quoteId, Quote updatedQuote) {
        Quote existingQuote = quoteRepository.findById(quoteId)
                .orElseThrow(() -> new QuoteNotFoundException(quoteId));

        existingQuote.setContent(updatedQuote.getContent());
        existingQuote.setCreated(new Date());

        return quoteRepository.save(existingQuote);
    }

    @Override
    @Transactional
    public void deleteQuote(Long quoteId) {
        if (quoteRepository.existsById(quoteId)) {
            voteRepository.deleteAllByQuoteId(quoteId);
            quoteRepository.deleteById(quoteId);
        } else {
            throw new QuoteNotFoundException(quoteId);
        }
    }

    @Override
    public List<Quote> getAllByUserId(Long id) {
        return quoteRepository.findAllByUserId(id);
    }
}
