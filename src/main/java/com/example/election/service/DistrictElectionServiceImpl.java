package com.example.election.service;

import com.example.election.dto.DistrictElectionDto;
import com.example.election.entities.District;
import com.example.election.entities.DistrictElection;
import com.example.election.entities.Election;
import com.example.election.repository.DistrictElectionRepository;
import com.example.election.repository.DistrictRepository;
import com.example.election.repository.ElectionRepository;
import com.example.election.service.DistrictElectionService;
import com.example.election.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class DistrictElectionServiceImpl implements DistrictElectionService {

    @Autowired
    private DistrictElectionRepository districtElectionRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private ElectionRepository electionRepository;

    @Override
    public StandardResponse createDistrictElection(DistrictElectionDto districtElectionDto) {
        try {
            District district = districtRepository.findById(districtElectionDto.getDistrictId())
                    .orElseThrow(() -> new RuntimeException("District not found"));
            Election election = electionRepository.findById(districtElectionDto.getElectionId())
                    .orElseThrow(() -> new RuntimeException("Election not found"));

            DistrictElection districtElection = new DistrictElection();
            districtElection.setDistrict(district);
            districtElection.setElection(election);
            districtElection.setTotalVotes(districtElectionDto.getTotalVotes());
            districtElection.setTotalValidVotes(districtElectionDto.getTotalValidVotes());

            districtElectionRepository.save(districtElection);
            return new StandardResponse("SUCCESS", "District election created successfully", districtElection, HttpStatus.CREATED.value());
        } catch (Exception e) {
            return new StandardResponse("ERROR", e.getMessage(), null, HttpStatus.BAD_REQUEST.value());
        }
    }
}