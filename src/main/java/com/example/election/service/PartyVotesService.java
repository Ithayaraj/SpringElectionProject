package com.example.election.service;

import com.example.election.dto.PartyVotesDto;
import com.example.election.utils.StandardResponse;

import java.util.List;

public interface PartyVotesService {
    StandardResponse createPartyVotes(PartyVotesDto partyVotesDto);
    StandardResponse getPartyVotesSummary();
    StandardResponse getTotalVotes();
    StandardResponse getTotalValidVotes();
}