package com.example.election.service;

import com.example.election.dto.ElectionDto;
import com.example.election.utils.StandardResponse;

import java.util.List;

public interface ElectionService {
    StandardResponse createElection(ElectionDto electionDto);
    StandardResponse getAllElections();
}