package com.example.election.repository;

import com.example.election.entities.PartyVotes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartyVotesRepository extends JpaRepository<PartyVotes, Integer> {
    @Query("SELECT new map(p.party.partyName as partyName, SUM(p.votes) as votesCount) " +
            "FROM PartyVotes p GROUP BY p.party.partyName")
    List<?> getPartyVotesSummary();

    @Query("SELECT SUM(de.totalVotes) FROM DistrictElection de")
    Integer getTotalVotes();

    @Query("SELECT SUM(de.totalValidVotes) FROM DistrictElection de")
    Integer getTotalValidVotes();

    List<PartyVotes> findByDistrictElectionDistrictElectionId(Integer districtElectionId);
}