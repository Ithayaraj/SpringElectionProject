package com.example.election.repository;

import com.example.election.entities.SeatAllocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatAllocationRepository extends JpaRepository<SeatAllocation, Integer> {
    List<SeatAllocation> findByDistrictElectionDistrictElectionId(Integer districtElectionId);
}