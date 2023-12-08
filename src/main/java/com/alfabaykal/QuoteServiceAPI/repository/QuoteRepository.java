package com.alfabaykal.QuoteServiceAPI.repository;

import com.alfabaykal.QuoteServiceAPI.model.Quote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {
    Page<Quote> findAllByOrderByVotes(Pageable pageable);

    List<Quote> findAllByUserId(Long userId);

    Page<Quote> findAllByOrderByVotesDesc(Pageable pageable);

    @Query(value = "SELECT q FROM Quote q ORDER BY RANDOM() LIMIT 1")
    Optional<Quote> findRandomQuote();

    Page<Quote> findAllByOrderByCreatedDesc(Pageable pageable);

    Page<Quote> findAllByOrderByLastVoteDateDesc(Pageable pageable);
}
