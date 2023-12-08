package com.alfabaykal.QuoteServiceAPI.service;

import com.alfabaykal.QuoteServiceAPI.model.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    User getUserByEmail(String email);

    boolean existsByEmail(String email);
}
