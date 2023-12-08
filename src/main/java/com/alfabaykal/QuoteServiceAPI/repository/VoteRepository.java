package com.alfabaykal.QuoteServiceAPI.repository;

import com.alfabaykal.QuoteServiceAPI.model.Quote;
import com.alfabaykal.QuoteServiceAPI.model.User;
import com.alfabaykal.QuoteServiceAPI.model.Vote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findVoteByQuoteAndUser(Quote quote, User user);

    Page<Vote> findAllByOrderByVotedAtDesc(Pageable pageable);

    void deleteVoteById(Long voteId);

    void deleteAllByQuoteId(Long quoteId);
}
