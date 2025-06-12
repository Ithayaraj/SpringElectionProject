package com.example.election.service;

import com.example.election.dto.DistrictElectionDto;
import com.example.election.utils.StandardResponse;

public interface DistrictElectionService {
    StandardResponse createDistrictElection(DistrictElectionDto districtElectionDto);
}