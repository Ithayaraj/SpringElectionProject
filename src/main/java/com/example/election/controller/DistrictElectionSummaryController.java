package com.example.election.controller;

import com.example.election.dto.DistrictElectionSummaryDto;
import com.example.election.service.DistrictElectionSummaryService;
import com.example.election.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/district-election-summaries")
public class DistrictElectionSummaryController {

    @Autowired
    private DistrictElectionSummaryService summaryService;

    @PostMapping
    public ResponseEntity<StandardResponse> createDistrictElectionSummary(@RequestBody DistrictElectionSummaryDto summaryDto) {
        try {
            summaryService.createDistrictElectionSummary(summaryDto);
            return ResponseEntity.ok(
                    new StandardResponse("SUCCESS", "District election summary created successfully", null, 0)
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    new StandardResponse("ERROR", "Failed to create summary: " + e.getMessage(), null, 400)
            );
        }
    }

}