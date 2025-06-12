package com.example.election.controller;

import com.example.election.dto.PartyDto;
import com.example.election.service.PartyService;
import com.example.election.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/parties")
public class PartyController {

    @Autowired
    private PartyService partyService;

    @PostMapping
    public ResponseEntity<StandardResponse> createParty(@RequestBody PartyDto partyDto) {
        return ResponseEntity.ok(partyService.createParty(partyDto));
    }

    @GetMapping
    public ResponseEntity<StandardResponse> getAllParties() {
        return ResponseEntity.ok(partyService.getAllParties());
    }

    @PutMapping("/{id}")
    public ResponseEntity<StandardResponse> updateParty(@PathVariable Integer id, @RequestBody PartyDto partyDto) {
        return ResponseEntity.ok(partyService.updateParty(id, partyDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> deleteParty(@PathVariable Integer id) {
        return ResponseEntity.ok(partyService.deleteParty(id));
    }

    @GetMapping("/count")
    public ResponseEntity<StandardResponse> getPartyCount() {
        return ResponseEntity.ok(partyService.getPartyCount());
    }
}