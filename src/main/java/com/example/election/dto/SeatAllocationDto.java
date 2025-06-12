package com.example.election.dto;

import lombok.Data;

@Data
public class SeatAllocationDto {
    private Integer seatAllocationId;
    private Integer bonusRound;
    private Integer firstRound;
    private Integer secondRound;
    private Integer finalAllocation;
    private Integer districtElectionId;
    private Integer partyId;
}