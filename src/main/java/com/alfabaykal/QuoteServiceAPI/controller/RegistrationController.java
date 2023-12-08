package com.alfabaykal.QuoteServiceAPI.controller;

import com.alfabaykal.QuoteServiceAPI.dto.UserRegistrationDto;
import com.alfabaykal.QuoteServiceAPI.model.User;
import com.alfabaykal.QuoteServiceAPI.service.RegistrationService;
import com.alfabaykal.QuoteServiceAPI.service.UserService;
import com.alfabaykal.QuoteServiceAPI.util.EntityDtoConverter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Registration", description = "User registration")
@RestController
@RequestMapping("/v1/registration")
public class RegistrationController {
    private final RegistrationService registrationService;
    private final UserService userService;
    private final EntityDtoConverter entityDtoConverter;

    public RegistrationController(RegistrationService registrationService, UserService userService, EntityDtoConverter entityDtoConverter) {
        this.userService = userService;
        this.entityDtoConverter = entityDtoConverter;
        this.registrationService = registrationService;
    }

    @Operation(summary = "Register new user")
    @PostMapping("/registration")
    public ResponseEntity<String> register(@Valid @RequestBody UserRegistrationDto userRegistrationDto,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new RuntimeException("User creation failed: " + bindingResult.getAllErrors());
        }
        registrationService.register(entityDtoConverter.convertUserRegistrationDtoToUser(userRegistrationDto));
        return ResponseEntity.ok("User register successfully");
    }

    @GetMapping("/all")
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

}
