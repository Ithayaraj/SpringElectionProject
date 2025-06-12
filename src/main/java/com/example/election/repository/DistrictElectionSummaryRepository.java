package com.example.election.repository;

import com.example.election.entities.DistrictElectionSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.ScopedValue;
import java.util.Optional;

@Repository
public interface DistrictElectionSummaryRepository extends JpaRepository<DistrictElectionSummary, Integer> {
    Optional<DistrictElectionSummary> findByDistrictElection_DistrictElectionId(Integer districtElectionId);

    Optional<Object> findByDistrictElectionDistrictElectionId(Integer districtElectionId);
}