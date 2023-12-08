package com.alfabaykal.QuoteServiceAPI.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "User information")
@Getter
@Setter
public class UserDto {
    private Long id;
}
