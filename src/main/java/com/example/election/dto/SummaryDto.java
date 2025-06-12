package com.example.election.dto;

public class SummaryDto {
    private int disqualifyVotes;
    private int threshold5Percent;

    public SummaryDto(int disqualifyVotes, int threshold5Percent) {
        this.disqualifyVotes = disqualifyVotes;
        this.threshold5Percent = threshold5Percent;
    }

    // getters
}
