package com.example.election.controller;

import com.example.election.dto.SeatAllocationDto;
import com.example.election.service.SeatAllocationService;
import com.example.election.service.SeatAllocationServiceImpl;
import com.example.election.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seat-allocations")
public class SeatAllocationController {

    @Autowired
    private SeatAllocationService seatAllocationService;

    @PostMapping
    public ResponseEntity<StandardResponse> createSeatAllocation(@RequestBody SeatAllocationDto seatAllocationDto) {
        try {
            seatAllocationService.createSeatAllocation(seatAllocationDto);
            return ResponseEntity.ok(
                    new StandardResponse("SUCCESS", "Seat allocation created successfully", null, 200)
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    new StandardResponse("ERROR", "Failed to create seat allocation: " + e.getMessage(), null, 400)
            );
        }
    }


    @PostMapping("/calculate/{districtElectionId}")
    public ResponseEntity<StandardResponse> calculateSeatAllocation(@PathVariable Integer districtElectionId) {
        return ResponseEntity.ok(((SeatAllocationServiceImpl) seatAllocationService).calculateSeatAllocation(districtElectionId));
    }
}