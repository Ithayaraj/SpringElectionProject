package com.example.election.controller;

import com.example.election.dto.ElectionDto;
import com.example.election.service.ElectionService;
import com.example.election.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/elections")
public class ElectionController {

    @Autowired
    private ElectionService electionService;

    @PostMapping
    public ResponseEntity<StandardResponse> createElection(@RequestBody ElectionDto electionDto) {
        return ResponseEntity.ok(electionService.createElection(electionDto));
    }

    @GetMapping
    public ResponseEntity<StandardResponse> getAllElections() {
        return ResponseEntity.ok(electionService.getAllElections());
    }
}