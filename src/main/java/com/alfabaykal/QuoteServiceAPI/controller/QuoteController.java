package com.alfabaykal.QuoteServiceAPI.controller;

import com.alfabaykal.QuoteServiceAPI.dto.AddQuoteDto;
import com.alfabaykal.QuoteServiceAPI.dto.GetQuoteDto;
import com.alfabaykal.QuoteServiceAPI.dto.UpdateQuoteDto;
import com.alfabaykal.QuoteServiceAPI.service.QuoteService;
import com.alfabaykal.QuoteServiceAPI.util.EntityDtoConverter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Quotes", description = "Methods for quotes managing")
@RestController
@RequestMapping("/v1/quotes")
public class QuoteController {
    private final QuoteService quoteService;
    private final EntityDtoConverter entityDtoConverter;

    public QuoteController(QuoteService quoteService, EntityDtoConverter entityDtoConverter) {
        this.quoteService = quoteService;
        this.entityDtoConverter = entityDtoConverter;
    }

    @Operation(summary = "Add new quote")
    @PostMapping
    public GetQuoteDto addQuote(@Valid @RequestBody AddQuoteDto addQuoteDto) {
        return entityDtoConverter
                .convertQuoteToGetQuoteDto(quoteService.saveQuote(entityDtoConverter
                        .convertAddQuoteDtoToQuote(addQuoteDto)));
    }

    @Operation(summary = "Get quote by ID")
    @GetMapping("/{quoteId}")
    public GetQuoteDto getQuote(@Parameter(description = "Quote ID")
                                @PathVariable Long quoteId) {
        return entityDtoConverter.convertQuoteToGetQuoteDto(quoteService.getQuoteById(quoteId));
    }

    @Operation(summary = "Get random quote")
    @GetMapping("/random")
    public GetQuoteDto getRandomQuote() {
        return entityDtoConverter.convertQuoteToGetQuoteDto(quoteService.getRandomQuote());
    }

    @Operation(summary = "Get top rated quotes")
    @GetMapping("/top")
    public List<GetQuoteDto> getTopQuotes(Pageable pageable) {
        return quoteService.getTopRatedQuotes(pageable).stream()
                .map(entityDtoConverter::convertQuoteToGetQuoteDto).toList();
    }

    @Operation(summary = "Get top rated quotes")
    @GetMapping("/flop")
    public List<GetQuoteDto> getLowestRatedQuotes(Pageable pageable) {
        return quoteService.getLowestRatedQuotes(pageable).stream()
                .map(entityDtoConverter::convertQuoteToGetQuoteDto).toList();
    }

    @Operation(summary = "Get last added quotes")
    @GetMapping("/last_added")
    public List<GetQuoteDto> getLastAddedQuotes(Pageable pageable) {
        return quoteService.getLastQuotes(pageable).stream()
                .map(entityDtoConverter::convertQuoteToGetQuoteDto).toList();
    }

    @Operation(summary = "Get last voted quotes")
    @GetMapping("/last_voted")
    public List<GetQuoteDto> getLastVotedQuotes(Pageable pageable) {
        return quoteService.getLastVotedQuotes(pageable).stream()
                .map(entityDtoConverter::convertQuoteToGetQuoteDto).toList();
    }

    @Operation(summary = "Update an existing quote")
    @PatchMapping("/{quoteId}")
    public ResponseEntity<String> updateQuote(@Parameter(description = "Quote ID")
                                              @PathVariable Long quoteId,
                                              @Valid @RequestBody UpdateQuoteDto updateQuoteDto) {
        quoteService.updateQuote(quoteId, entityDtoConverter.convertUpdateQuoteDtoToQuote(updateQuoteDto));
        return ResponseEntity.ok("Quote has been updated successfully");
    }

    @Operation(summary = "Delete quote")
    @DeleteMapping("/{quoteId}")
    public ResponseEntity<String> deleteQuote(@Parameter(description = "Quote ID")
                                              @PathVariable Long quoteId) {
        quoteService.deleteQuote(quoteId);
        return ResponseEntity.ok("Quote has been added successfully");
    }
}
