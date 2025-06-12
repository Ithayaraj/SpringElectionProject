package com.example.election.service;

import com.example.election.dto.PartyDto;
import com.example.election.entities.Party;
import com.example.election.repository.PartyRepository;
import com.example.election.service.PartyService;
import com.example.election.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartyServiceImpl implements PartyService {

    @Autowired
    private PartyRepository partyRepository;

    @Override
    public StandardResponse createParty(PartyDto partyDto) {
        try {
            Party party = new Party();
            party.setPartyName(partyDto.getPartyName());
            partyRepository.save(party);
            return new StandardResponse("SUCCESS", "Party created successfully", party, HttpStatus.CREATED.value());
        } catch (Exception e) {
            return new StandardResponse("ERROR", e.getMessage(), null, HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public StandardResponse getAllParties() {
        try {
            List<PartyDto> parties = partyRepository.findAll().stream()
                    .map(p -> new PartyDto(p.getPartyId(), p.getPartyName()))
                    .collect(Collectors.toList());
            return new StandardResponse("SUCCESS", "Parties retrieved successfully", parties, HttpStatus.OK.value());
        } catch (Exception e) {
            return new StandardResponse("ERROR", e.getMessage(), null, HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public StandardResponse updateParty(Integer id, PartyDto partyDto) {
        try {
            Party party = partyRepository.findById(id).orElseThrow(() -> new RuntimeException("Party not found"));
            party.setPartyName(partyDto.getPartyName());
            partyRepository.save(party);
            return new StandardResponse("SUCCESS", "Party updated successfully", party, HttpStatus.OK.value());
        } catch (Exception e) {
            return new StandardResponse("ERROR", e.getMessage(), null, HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public StandardResponse deleteParty(Integer id) {
        try {
            partyRepository.deleteById(id);
            return new StandardResponse("SUCCESS", "Party deleted successfully", null, HttpStatus.OK.value());
        } catch (Exception e) {
            return new StandardResponse("ERROR", e.getMessage(), null, HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public StandardResponse getPartyCount() {
        try {
            long count = partyRepository.count();
            return new StandardResponse("SUCCESS", "Party count retrieved", count, HttpStatus.OK.value());
        } catch (Exception e) {
            return new StandardResponse("ERROR", e.getMessage(), null, HttpStatus.BAD_REQUEST.value());
        }
    }
}