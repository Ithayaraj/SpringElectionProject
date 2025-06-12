package com.example.election.repository;

import com.example.election.entities.DistrictElection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictElectionRepository extends JpaRepository<DistrictElection, Integer> {
}