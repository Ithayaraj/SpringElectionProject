package com.example.election.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor


@Data
public class DistrictElectionSummaryDto {
    private Integer districtElectionIdSummary;
    private Integer disqualifyVotes;
    private Integer disqualifyPartyCount;
    private Integer threshold5Percent;
    private Integer districtElectionId;
}