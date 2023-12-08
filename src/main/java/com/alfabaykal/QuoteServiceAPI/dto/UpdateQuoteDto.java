package com.alfabaykal.QuoteServiceAPI.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "Update quote information")
@Getter
@Setter
public class UpdateQuoteDto {
    @NotNull
    @Size(min = 1, max = 1000, message = "Quote size should be between 1 and 1000 symbols")
    private String content;
}
