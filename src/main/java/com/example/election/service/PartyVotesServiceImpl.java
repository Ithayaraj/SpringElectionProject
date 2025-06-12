package com.example.election.service;

import com.example.election.dto.PartyVotesDto;
import com.example.election.entities.DistrictElection;
import com.example.election.entities.Party;
import com.example.election.entities.PartyVotes;
import com.example.election.repository.DistrictElectionRepository;
import com.example.election.repository.PartyRepository;
import com.example.election.repository.PartyVotesRepository;
import com.example.election.service.PartyVotesService;
import com.example.election.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartyVotesServiceImpl implements PartyVotesService {

    @Autowired
    private PartyVotesRepository partyVotesRepository;

    @Autowired
    private DistrictElectionRepository districtElectionRepository;

    @Autowired
    private PartyRepository partyRepository;

    @Override
    public StandardResponse createPartyVotes(PartyVotesDto partyVotesDto) {
        try {
            DistrictElection districtElection = districtElectionRepository.findById(partyVotesDto.getDistrictElectionId())
                    .orElseThrow(() -> new RuntimeException("District election not found"));
            Party party = partyRepository.findById(partyVotesDto.getPartyId())
                    .orElseThrow(() -> new RuntimeException("Party not found"));

            PartyVotes partyVotes = new PartyVotes();
            partyVotes.setDistrictElection(districtElection);
            partyVotes.setParty(party);
            partyVotes.setVotes(partyVotesDto.getVotes());

            partyVotesRepository.save(partyVotes);
            return new StandardResponse("SUCCESS", "Party votes created successfully", partyVotes, HttpStatus.CREATED.value());
        } catch (Exception e) {
            return new StandardResponse("ERROR", e.getMessage(), null, HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public StandardResponse getPartyVotesSummary() {
        try {
            List<?> summary = partyVotesRepository.getPartyVotesSummary();
            return new StandardResponse("SUCCESS", "Party votes summary retrieved", summary, HttpStatus.OK.value());
        } catch (Exception e) {
            return new StandardResponse("ERROR", e.getMessage(), null, HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public StandardResponse getTotalVotes() {
        try {
            Integer totalVotes = partyVotesRepository.getTotalVotes();
            return new StandardResponse("SUCCESS", "Total votes retrieved", totalVotes, HttpStatus.OK.value());
        } catch (Exception e) {
            return new StandardResponse("ERROR", e.getMessage(), null, HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public StandardResponse getTotalValidVotes() {
        try {
            Integer totalValidVotes = partyVotesRepository.getTotalValidVotes();
            return new StandardResponse("SUCCESS", "Total valid votes retrieved", totalValidVotes, HttpStatus.OK.value());
        } catch (Exception e) {
            return new StandardResponse("ERROR", e.getMessage(), null, HttpStatus.BAD_REQUEST.value());
        }
    }
}