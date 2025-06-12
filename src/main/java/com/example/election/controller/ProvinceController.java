package com.example.election.controller;

import com.example.election.dto.ProvinceDto;
import com.example.election.service.ProvinceService;
import com.example.election.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/provinces")
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;

    @PostMapping
    public ResponseEntity<StandardResponse> createProvince(@RequestBody ProvinceDto provinceDto) {
        return ResponseEntity.ok(provinceService.createProvince(provinceDto));
    }

    @GetMapping
    public ResponseEntity<StandardResponse> getAllProvinces() {
        return ResponseEntity.ok(provinceService.getAllProvinces());
    }

    @PutMapping("/{id}")
    public ResponseEntity<StandardResponse> updateProvince(@PathVariable Integer id, @RequestBody ProvinceDto provinceDto) {
        return ResponseEntity.ok(provinceService.updateProvince(id, provinceDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> deleteProvince(@PathVariable Integer id) {
        return ResponseEntity.ok(provinceService.deleteProvince(id));
    }

    @GetMapping("/count")
    public ResponseEntity<StandardResponse> getProvinceCount() {
        return ResponseEntity.ok(provinceService.getProvinceCount());
    }
}