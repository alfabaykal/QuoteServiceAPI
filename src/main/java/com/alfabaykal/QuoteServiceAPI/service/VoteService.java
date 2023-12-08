package com.alfabaykal.QuoteServiceAPI.service;

import com.alfabaykal.QuoteServiceAPI.model.Vote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface VoteService {
    Optional<Vote> getVoteByQuoteAndUser(Long quoteId, Long voterId);

    Page<Vote> getLastVotes(Pageable pageable);

    void vote(Long quoteId, Long voterId, int voteValue);

    void deleteVote(Long voteId);
}
