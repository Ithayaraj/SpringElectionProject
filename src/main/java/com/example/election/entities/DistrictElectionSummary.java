package com.example.election.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "district_election_summary")
public class DistrictElectionSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "district_election_id_summary")
    private Integer districtElectionIdSummary;

    @Column(name = "disqualify_votes", nullable = false)
    private Integer disqualifyVotes;

    @Column(name = "disqualify_party_count", nullable = false)
    private Integer disqualifyPartyCount;

    @Column(name = "threshold_5_percent", nullable = false)
    private Integer threshold5Percent;

    @OneToOne
    @JoinColumn(name = "district_election_id", nullable = false)
    private DistrictElection districtElection;
}