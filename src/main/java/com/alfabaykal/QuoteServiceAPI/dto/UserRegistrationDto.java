package com.alfabaykal.QuoteServiceAPI.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "User registration information")
@Getter
@Setter
public class UserRegistrationDto {
    @NotEmpty
    @Size(min = 1, max = 255, message = "name size should be between 1 and 255 symbols")
    private String name;

    @Email
    @Size(max = 255, message = "email should be shorter than 255 symbols")
    private String email;

    @Size(min = 8, max = 20, message = "Password size should be between 8 and 20 symbols")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$",
            message = "Password must have at least 1 uppercase, 1 lowercase, 1 number, and 1 special symbol")
    private String password;
}
