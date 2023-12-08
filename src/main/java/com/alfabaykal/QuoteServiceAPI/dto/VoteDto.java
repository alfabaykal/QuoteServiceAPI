package com.alfabaykal.QuoteServiceAPI.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "Vote information")
@Getter
@Setter
public class VoteDto {
    @NotNull(message = "quoteId must be provided")
    private Long quoteId;
    @NotNull(message = "voterId must be provided")
    private Long voterId;
    private int voteValue;
}
