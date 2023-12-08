package com.alfabaykal.QuoteServiceAPI.init;

import com.alfabaykal.QuoteServiceAPI.model.Quote;
import com.alfabaykal.QuoteServiceAPI.model.User;
import com.alfabaykal.QuoteServiceAPI.model.Vote;
import com.alfabaykal.QuoteServiceAPI.repository.QuoteRepository;
import com.alfabaykal.QuoteServiceAPI.repository.UserRepository;
import com.alfabaykal.QuoteServiceAPI.repository.VoteRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class DataInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final QuoteRepository quoteRepository;
    private final VoteRepository voteRepository;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder, QuoteRepository quoteRepository, VoteRepository voteRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.quoteRepository = quoteRepository;
        this.voteRepository = voteRepository;
    }

    @PostConstruct
    public void init() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            User user = createUser("user" + i, "user" + i + "@example.com", "P@ssw0rd" + i);
            users.add(user);
            for (int j = 0; j < 5; j++) {
                createQuote("Quote " + i + " content " + j, user);
            }
        }
        for (int i = 0; i < 25; i++) {
            simulateVotes(users);
        }
    }

    private User createUser(String name, String email, String password) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    private void createQuote(String content, User user) {
        Quote quote = new Quote();
        quote.setContent(content);
        quote.setUser(user);
        quote.setCreated(generateRandomDate());
        quoteRepository.save(quote);

    }

    private void simulateVotes(List<User> users) {
        for (User user : users) {
            List<Quote> quotes = quoteRepository.findAllByUserId(user.getId());
            for (Quote quote : quotes) {
                int voteValue = ThreadLocalRandom.current().nextBoolean() ? 1 : -1;
                Vote vote = new Vote();
                vote.setQuote(quote);
                vote.setUser(user);
                vote.setVoteValue(voteValue);
                vote.setVotedAt(generateRandomDate());
                voteRepository.save(vote);
                quote.voteForQuote(voteValue);
                quote.setLastVoteDate(vote.getVotedAt());
                quoteRepository.save(quote);
            }
        }
    }

    private Date generateRandomDate() {
        long randomTimestamp = ThreadLocalRandom.current().nextLong(
                Timestamp.valueOf("2010-01-01 00:00:00").getTime(),
                Timestamp.valueOf("2020-12-31 23:59:59").getTime());
        return new Date(randomTimestamp);
    }
}
