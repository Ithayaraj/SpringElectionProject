package com.example.election.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "party")
public class Party {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "party_id")
    private Integer partyId;

    @Column(name = "party_name", unique = true, nullable = false)
    private String partyName;

    @OneToMany(mappedBy = "party", cascade = CascadeType.ALL)
    private List<PartyVotes> partyVotes;

    @OneToMany(mappedBy = "party", cascade = CascadeType.ALL)
    private List<SeatAllocation> seatAllocations;
}