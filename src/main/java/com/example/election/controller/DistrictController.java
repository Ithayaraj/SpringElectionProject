package com.example.election.controller;

import com.example.election.dto.DistrictDto;
import com.example.election.service.DistrictService;
import com.example.election.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/districts")
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    @PostMapping
    public ResponseEntity<StandardResponse> createDistrict(@RequestBody DistrictDto districtDto) {
        return ResponseEntity.ok(districtService.createDistrict(districtDto));
    }

    @GetMapping("/by-province/{provinceId}")
    public ResponseEntity<StandardResponse> getDistrictsByProvince(@PathVariable Integer provinceId) {
        return ResponseEntity.ok(districtService.getDistrictsByProvince(provinceId));
    }

    @GetMapping("/count")
    public ResponseEntity<StandardResponse> getDistrictCount() {
        return ResponseEntity.ok(districtService.getDistrictCount());
    }

    @GetMapping("/total-seats")
    public ResponseEntity<StandardResponse> getTotalSeatCount() {
        return ResponseEntity.ok(districtService.getTotalSeatCount());
    }
}