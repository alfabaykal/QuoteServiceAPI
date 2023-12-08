package com.alfabaykal.QuoteServiceAPI.service.impl;

import com.alfabaykal.QuoteServiceAPI.exception.VoteNotFoundException;
import com.alfabaykal.QuoteServiceAPI.model.Quote;
import com.alfabaykal.QuoteServiceAPI.model.User;
import com.alfabaykal.QuoteServiceAPI.model.Vote;
import com.alfabaykal.QuoteServiceAPI.repository.VoteRepository;
import com.alfabaykal.QuoteServiceAPI.service.QuoteService;
import com.alfabaykal.QuoteServiceAPI.service.UserService;
import com.alfabaykal.QuoteServiceAPI.service.VoteService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class VoteServiceImpl implements VoteService {
    private final VoteRepository voteRepository;
    private final QuoteService quoteService;
    private final UserService userService;

    public VoteServiceImpl(VoteRepository voteRepository, QuoteService quoteService, UserService userService) {
        this.voteRepository = voteRepository;
        this.quoteService = quoteService;
        this.userService = userService;
    }

    @Override
    public Optional<Vote> getVoteByQuoteAndUser(Long quoteId, Long voterId) {
        return voteRepository.findVoteByQuoteAndUser(quoteService.getQuoteById(quoteId),
                userService.getUserById(voterId));
    }

    @Override
    public Page<Vote> getLastVotes(Pageable pageable) {
        return voteRepository.findAllByOrderByVotedAtDesc(pageable);
    }

    @Override
    @Transactional
    public void vote(Long quoteId, Long voterId, int voteValue) {
        Quote votedQuote = quoteService.getQuoteById(quoteId);
        User voter = userService.getUserById(voterId);

        if (isVoted(quoteId, voterId)) {
            handleExistingVote(votedQuote, voter, voteValue);
        } else {
            handleNewVote(votedQuote, voter, voteValue);
        }
    }

    @Override
    @Transactional
    public void deleteVote(Long voteId) {
        Optional<Vote> voteOptional = voteRepository.findById(voteId);
        if (voteOptional.isPresent()) {
            Vote vote = voteOptional.get();
            Quote quote = vote.getQuote();
            quote.setVotes(quote.getVotes() - vote.getVoteValue());
            quoteService.saveQuote(quote);
            voteRepository.deleteVoteById(voteId);
        } else {
            throw new VoteNotFoundException(voteId);
        }
    }

//    @Override
//    public void deleteAllVotesByQuoteId(Long quoteId) {
//        voteRepository.deleteAllByQuoteId(quoteId);
//    }

    private void handleExistingVote(Quote votedQuote, User voter, int voteValue) {
        Vote vote = voteRepository.findVoteByQuoteAndUser(votedQuote, voter)
                .orElseThrow(VoteNotFoundException::new);

        if (voteValue != vote.getVoteValue()) {
            vote.setVoteValue(voteValue);

            votedQuote.voteForQuote(voteValue * 2);
            votedQuote.setLastVoteDate(new Date());

            voteRepository.save(vote);
            quoteService.saveQuote(votedQuote);
        }
    }

    private void handleNewVote(Quote votedQuote, User voter, int voteValue) {
        Vote vote = new Vote();
        vote.setQuote(votedQuote);
        vote.setUser(voter);
        vote.setVoteValue(voteValue);

        votedQuote.voteForQuote(voteValue);
        votedQuote.setLastVoteDate(new Date());

        voteRepository.save(vote);
        quoteService.saveQuote(votedQuote);
    }

    private boolean isVoted(Long quoteId, Long voterId) {
        return getVoteByQuoteAndUser(quoteId, voterId).isPresent();
    }
}
