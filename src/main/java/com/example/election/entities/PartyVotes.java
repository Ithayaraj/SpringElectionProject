package com.example.election.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "party_votes")
public class PartyVotes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "party_votes_id")
    private Integer partyVotesId;

    @Column(name = "votes", nullable = false)
    private Integer votes;

    @ManyToOne
    @JoinColumn(name = "district_election_id", nullable = false)
    private DistrictElection districtElection;

    @ManyToOne
    @JoinColumn(name = "party_id", nullable = false)
    private Party party;
}