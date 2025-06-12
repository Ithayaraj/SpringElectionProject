package com.example.election.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartyVotesDto {
    private Integer partyVotesId;
    private Integer votes;
    private Integer districtElectionId;
    private Integer partyId;
}