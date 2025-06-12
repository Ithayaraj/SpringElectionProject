package com.example.election.service;

import com.example.election.dto.DistrictElectionSummaryDto;
import com.example.election.entities.DistrictElection;
import com.example.election.entities.DistrictElectionSummary;
import com.example.election.entities.PartyVotes;
import com.example.election.repository.DistrictElectionRepository;
import com.example.election.repository.DistrictElectionSummaryRepository;
import com.example.election.repository.PartyVotesRepository;
import com.example.election.service.DistrictElectionSummaryService;
import com.example.election.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DistrictElectionSummaryServiceImpl implements DistrictElectionSummaryService {

    @Autowired
    private DistrictElectionSummaryRepository summaryRepository;

    @Autowired
    private DistrictElectionRepository districtElectionRepository;

    @Autowired
    private PartyVotesRepository partyVotesRepository;

    @Override
    public StandardResponse createDistrictElectionSummary(DistrictElectionSummaryDto summaryDto) {
        try {
            // Find the district election
            DistrictElection districtElection = districtElectionRepository.findById(summaryDto.getDistrictElectionId())
                    .orElseThrow(() -> new RuntimeException("District election not found"));

            // Check if summary already exists for this district election
            Optional<DistrictElectionSummary> existingSummary = summaryRepository.findByDistrictElection_DistrictElectionId(districtElection.getDistrictElectionId());
            if (existingSummary.isPresent()) {
                return new StandardResponse("ERROR", "Summary already exists for this district election", null, HttpStatus.BAD_REQUEST.value());
            }

            // Calculate threshold (5% of total valid votes)
            int threshold = (int) (districtElection.getTotalValidVotes() * 0.05);

            // Get all party votes for this district election
            List<PartyVotes> partyVotes = partyVotesRepository.findByDistrictElectionDistrictElectionId(districtElection.getDistrictElectionId());

            // Calculate disqualify votes and party count
            int disqualifyVotes = 0;
            int disqualifyPartyCount = 0;

            for (PartyVotes pv : partyVotes) {
                if (pv.getVotes() < threshold) {
                    disqualifyVotes += pv.getVotes();
                    disqualifyPartyCount++;
                }
            }

            // Create and save the summary
            DistrictElectionSummary summary = new DistrictElectionSummary();
            summary.setDistrictElection(districtElection);
            summary.setDisqualifyVotes(disqualifyVotes);
            summary.setDisqualifyPartyCount(disqualifyPartyCount);
            summary.setThreshold5Percent(threshold);

            DistrictElectionSummary savedSummary = summaryRepository.save(summary);

            return new StandardResponse(
                    "SUCCESS",
                    "District election summary created successfully",
                    savedSummary,
                    HttpStatus.CREATED.value()
            );

        } catch (Exception e) {
            return new StandardResponse(
                    "ERROR",
                    "Failed to create district election summary: " + e.getMessage(),
                    null,
                    HttpStatus.BAD_REQUEST.value()
            );
        }
    }
}