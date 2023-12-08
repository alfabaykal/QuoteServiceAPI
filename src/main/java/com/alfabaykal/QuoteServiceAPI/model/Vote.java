package com.alfabaykal.QuoteServiceAPI.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "votes")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "quote_id", nullable = false)
    private Quote quote;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "vote_value", nullable = false, columnDefinition = "INT CHECK (vote_value IN (1, -1))")
    private int voteValue;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "voted_at")
    private Date votedAt;

    @AssertTrue(message = "Vote value must be equal 1 or -1")
    private boolean isValidVoteValue() {
        return voteValue == 1 || voteValue == -1;
    }
}
