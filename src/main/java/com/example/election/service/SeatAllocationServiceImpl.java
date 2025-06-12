package com.example.election.service;

import com.example.election.dto.SeatAllocationDto;
import com.example.election.entities.*;
import com.example.election.repository.*;
import com.example.election.service.SeatAllocationService;
import com.example.election.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatAllocationServiceImpl implements SeatAllocationService {

    @Autowired
    private SeatAllocationRepository seatAllocationRepository;

    @Autowired
    private DistrictElectionRepository districtElectionRepository;

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private PartyVotesRepository partyVotesRepository;

    @Autowired
    private DistrictElectionSummaryRepository summaryRepository;

    @Override
    public StandardResponse createSeatAllocation(SeatAllocationDto seatAllocationDto) {
        try {
            DistrictElection districtElection = districtElectionRepository.findById(seatAllocationDto.getDistrictElectionId())
                    .orElseThrow(() -> new RuntimeException("District election not found"));
            Party party = partyRepository.findById(seatAllocationDto.getPartyId())
                    .orElseThrow(() -> new RuntimeException("Party not found"));

            SeatAllocation seatAllocation = new SeatAllocation();
            seatAllocation.setDistrictElection(districtElection);
            seatAllocation.setParty(party);
            seatAllocation.setBonusRound(seatAllocationDto.getBonusRound());
            seatAllocation.setFirstRound(seatAllocationDto.getFirstRound());
            seatAllocation.setSecondRound(seatAllocationDto.getSecondRound());
            seatAllocation.setFinalAllocation(seatAllocationDto.getFinalAllocation());

            seatAllocationRepository.save(seatAllocation);
            return new StandardResponse("SUCCESS", "Seat allocation created successfully", seatAllocation, HttpStatus.CREATED.value());
        } catch (Exception e) {
            return new StandardResponse("ERROR", e.getMessage(), null, HttpStatus.BAD_REQUEST.value());
        }
    }

    public StandardResponse calculateSeatAllocation(Integer districtElectionId) {
        try {
            DistrictElection districtElection = districtElectionRepository.findById(districtElectionId)
                    .orElseThrow(() -> new RuntimeException("District election not found"));

            DistrictElectionSummary summary = (DistrictElectionSummary) summaryRepository.findByDistrictElectionDistrictElectionId(districtElectionId)
                    .orElseThrow(() -> new RuntimeException("District election summary not found"));

            // Get qualifying parties (votes >= threshold)
            List<PartyVotes> allPartyVotes = partyVotesRepository.findByDistrictElectionDistrictElectionId(districtElectionId);
            List<PartyVotes> qualifyingPartyVotes = allPartyVotes.stream()
                    .filter(pv -> pv.getVotes() >= summary.getThreshold5Percent())
                    .collect(Collectors.toList());

            int totalQualifyingVotes = qualifyingPartyVotes.stream().mapToInt(PartyVotes::getVotes).sum();
            int totalSeats = districtElection.getDistrict().getSeatCount();

            // Bonus round - give 1 seat to party with highest votes
            PartyVotes highestVotesParty = qualifyingPartyVotes.stream()
                    .max(Comparator.comparingInt(PartyVotes::getVotes))
                    .orElseThrow(() -> new RuntimeException("No qualifying parties found"));

            // First round - proportional allocation
            double votesPerSeat = (double) totalQualifyingVotes / totalSeats;
            int remainingSeats = totalSeats - 1; // subtract bonus seat

            for (PartyVotes pv : qualifyingPartyVotes) {
                SeatAllocation allocation = new SeatAllocation();
                allocation.setDistrictElection(districtElection);
                allocation.setParty(pv.getParty());

                // Set bonus seat
                if (pv.getParty().getPartyId().equals(highestVotesParty.getParty().getPartyId())) {
                    allocation.setBonusRound(1);
                } else {
                    allocation.setBonusRound(0);
                }

                // First round seats
                int firstRoundSeats = (int) (pv.getVotes() / votesPerSeat);
                allocation.setFirstRound(firstRoundSeats);
                remainingSeats -= firstRoundSeats;

                // Save initial allocation (second round will be calculated later)
                allocation.setFinalAllocation(allocation.getBonusRound() + allocation.getFirstRound());
                seatAllocationRepository.save(allocation);
            }

            // Second round - allocate remaining seats to parties with highest remainders
            List<SeatAllocation> allocations = seatAllocationRepository.findByDistrictElectionDistrictElectionId(districtElectionId);

            // Calculate remainders (votes - (firstRoundSeats * votesPerSeat))
            allocations.forEach(a -> {
                PartyVotes pv = qualifyingPartyVotes.stream()
                        .filter(p -> p.getParty().getPartyId().equals(a.getParty().getPartyId()))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Party not found"));

                double remainder = pv.getVotes() - (a.getFirstRound() * votesPerSeat);
                a.setSecondRound(0); // initialize
                a.setRemainder(remainder); // temporary field for sorting
            });

            // Sort by remainder descending and allocate remaining seats
            allocations.sort(Comparator.comparingDouble(SeatAllocation::getRemainder).reversed());

            for (int i = 0; i < remainingSeats && i < allocations.size(); i++) {
                SeatAllocation a = allocations.get(i);
                a.setSecondRound(1);
                a.setFinalAllocation(a.getBonusRound() + a.getFirstRound() + a.getSecondRound());
                seatAllocationRepository.save(a);
            }

            // For parties that didn't get second round seats
            for (int i = remainingSeats; i < allocations.size(); i++) {
                SeatAllocation a = allocations.get(i);
                a.setSecondRound(0);
                a.setFinalAllocation(a.getBonusRound() + a.getFirstRound());
                seatAllocationRepository.save(a);
            }

            return new StandardResponse("SUCCESS", "Seat allocation calculated successfully", null, HttpStatus.OK.value());
        } catch (Exception e) {
            return new StandardResponse("ERROR", e.getMessage(), null, HttpStatus.BAD_REQUEST.value());
        }
    }
}