package com.alfabaykal.QuoteServiceAPI.controller;

import com.alfabaykal.QuoteServiceAPI.dto.VoteDto;
import com.alfabaykal.QuoteServiceAPI.service.VoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Votes", description = "Methods for votes managing")
@RestController
@RequestMapping("/v1/votes")
public class VoteController {

    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @Operation(summary = "Vote for quote")
    @PostMapping
    public ResponseEntity<String> vote(@Valid @RequestBody VoteDto voteDto) {
        voteService.vote(voteDto.getQuoteId(), voteDto.getVoterId(), voteDto.getVoteValue());
        return ResponseEntity.ok("Vote has been recorded successfully");
    }

    @Operation(summary = "Delete vote")
    @DeleteMapping
    public ResponseEntity<String> deleteVote(@RequestParam @Parameter(description = "Vote ID to delete") Long voteId) {
        if (voteId == null) {
            return ResponseEntity.badRequest().body("voteId must be provided");
        }
        voteService.deleteVote(voteId);
        return ResponseEntity.ok("Vote has been deleted successfully");
    }
}
