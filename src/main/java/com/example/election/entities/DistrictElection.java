package com.example.election.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "district_election")
public class DistrictElection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "district_election_id")
    private Integer districtElectionId;

    @Column(name = "total_votes", nullable = false)
    private Integer totalVotes;

    @Column(name = "total_valid_votes", nullable = false)
    private Integer totalValidVotes;

    @ManyToOne
    @JoinColumn(name = "district_id", nullable = false)
    private District district;

    @ManyToOne
    @JoinColumn(name = "election_id", nullable = false)
    private Election election;

    @OneToMany(mappedBy = "districtElection", cascade = CascadeType.ALL)
    private List<PartyVotes> partyVotes;

    @OneToMany(mappedBy = "districtElection", cascade = CascadeType.ALL)
    private List<SeatAllocation> seatAllocations;

    @OneToOne(mappedBy = "districtElection", cascade = CascadeType.ALL)
    private DistrictElectionSummary districtElectionSummary;
}