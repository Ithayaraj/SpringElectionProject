package com.example.election.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistrictElectionDto {
    private Integer districtElectionId;
    private Integer totalVotes;
    private Integer totalValidVotes;
    private Integer districtId;
    private Integer electionId;
}