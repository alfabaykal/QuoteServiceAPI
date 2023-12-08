package com.alfabaykal.QuoteServiceAPI.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@Entity
@Table(name = "quotes")
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quote_id")
    private Long id;

    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    @Column(name = "vote_count")
    private int votes;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date created;

    @Column(name = "last_vote_date")
    private Date lastVoteDate;

    public Quote() {
        this.votes = 0;
    }

    public void voteForQuote(int voteValue) {
        votes = votes + voteValue;
    }
}
