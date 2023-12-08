package com.alfabaykal.QuoteServiceAPI.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "Quote information")
@Getter
@Setter
public class GetQuoteDto {
    private Long quoteId;
    private String content;
    private Long userId;
    private int votes;
}
