package com.alfabaykal.QuoteServiceAPI.service.impl;

import com.alfabaykal.QuoteServiceAPI.exception.EmailAlreadyExistsException;
import com.alfabaykal.QuoteServiceAPI.model.User;
import com.alfabaykal.QuoteServiceAPI.service.RegistrationService;
import com.alfabaykal.QuoteServiceAPI.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public RegistrationServiceImpl(UserService userService,
                                   PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(User user) {
        if (userService.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistsException();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
    }
}
