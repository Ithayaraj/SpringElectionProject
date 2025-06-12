package com.example.election.service;

import com.example.election.dto.ElectionDto;
import com.example.election.entities.Election;
import com.example.election.repository.ElectionRepository;
import com.example.election.service.ElectionService;
import com.example.election.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ElectionServiceImpl implements ElectionService {

    @Autowired
    private ElectionRepository electionRepository;

    @Override
    public StandardResponse createElection(ElectionDto electionDto) {
        try {
            Election election = new Election();
            election.setYear(electionDto.getYear());
            electionRepository.save(election);
            return new StandardResponse("SUCCESS", "Election created successfully", election, HttpStatus.CREATED.value());
        } catch (Exception e) {
            return new StandardResponse("ERROR", e.getMessage(), null, HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public StandardResponse getAllElections() {
        try {
            List<ElectionDto> elections = electionRepository.findAllByOrderByYearDesc().stream()
                    .map(e -> new ElectionDto(e.getElectionId(), e.getYear()))
                    .collect(Collectors.toList());
            return new StandardResponse("SUCCESS", "Elections retrieved successfully", elections, HttpStatus.OK.value());
        } catch (Exception e) {
            return new StandardResponse("ERROR", e.getMessage(), null, HttpStatus.BAD_REQUEST.value());
        }
    }
}