package com.example.election.controller;

import com.example.election.dto.DistrictElectionDto;
import com.example.election.service.DistrictElectionService;
import com.example.election.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/district-elections")
public class DistrictElectionController {

    @Autowired
    private DistrictElectionService districtElectionService;

    @PostMapping
    public ResponseEntity<StandardResponse> createDistrictElection(@RequestBody DistrictElectionDto districtElectionDto) {
        try {
            districtElectionService.createDistrictElection(districtElectionDto); // just save
            return ResponseEntity.ok(
                    new StandardResponse("SUCCESS", "District election created successfully", null, 200)
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    new StandardResponse("ERROR", "Failed to create district election: " + e.getMessage(), null, 400)
            );
        }
    }

}