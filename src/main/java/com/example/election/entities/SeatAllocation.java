package com.example.election.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "seat_allocation")
public class SeatAllocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_allocation_id")
    private Integer seatAllocationId;

    @Column(name = "bonus_round", columnDefinition = "INT DEFAULT 0")
    private Integer bonusRound = 0;

    @Column(name = "first_round", columnDefinition = "INT DEFAULT 0")
    private Integer firstRound = 0;

    @Column(name = "second_round", columnDefinition = "INT DEFAULT 0")
    private Integer secondRound = 0;

    @Column(name = "final_allocation", nullable = false)
    private Integer finalAllocation;

    @ManyToOne
    @JoinColumn(name = "district_election_id", nullable = false)
    private DistrictElection districtElection;

    @ManyToOne
    @JoinColumn(name = "party_id", nullable = false)
    private Party party;

    public void setRemainder(double remainder) {
    }

    public static double getRemainder(Object o) {
        return 0;
    }
}