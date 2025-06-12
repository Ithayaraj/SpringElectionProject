package com.example.election.service;

import com.example.election.dto.PartyDto;
import com.example.election.utils.StandardResponse;

import java.util.List;

public interface PartyService {
    StandardResponse createParty(PartyDto partyDto);
    StandardResponse getAllParties();
    StandardResponse updateParty(Integer id, PartyDto partyDto);
    StandardResponse deleteParty(Integer id);
    StandardResponse getPartyCount();
}