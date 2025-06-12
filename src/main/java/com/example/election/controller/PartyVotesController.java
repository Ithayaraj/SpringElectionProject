package com.example.election.controller;

import com.example.election.dto.PartyVotesDto;
import com.example.election.service.PartyVotesService;
import com.example.election.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/party-votes")
public class PartyVotesController {

    @Autowired
    private PartyVotesService partyVotesService;

    @PostMapping
    public ResponseEntity<StandardResponse> createPartyVotes(@RequestBody PartyVotesDto partyVotesDto) {
        try {
            partyVotesService.createPartyVotes(partyVotesDto); // just save
            return ResponseEntity.ok(
                    new StandardResponse("SUCCESS", "Party votes created successfully", null, 200)
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    new StandardResponse("ERROR", e.getMessage(), null, 400)
            );
        }    }

    @GetMapping("/summary")
    public ResponseEntity<StandardResponse> getPartyVotesSummary() {
        return ResponseEntity.ok(partyVotesService.getPartyVotesSummary());
    }

    @GetMapping("/total-votes")
    public ResponseEntity<StandardResponse> getTotalVotes() {
        return ResponseEntity.ok(partyVotesService.getTotalVotes());
    }

    @GetMapping("/total-valid-votes")
    public ResponseEntity<StandardResponse> getTotalValidVotes() {
        return ResponseEntity.ok(partyVotesService.getTotalValidVotes());
    }
}