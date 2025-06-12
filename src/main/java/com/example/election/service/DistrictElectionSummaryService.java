package com.example.election.service;

import com.example.election.dto.DistrictElectionSummaryDto;
import com.example.election.utils.StandardResponse;

public interface DistrictElectionSummaryService {
    StandardResponse createDistrictElectionSummary(DistrictElectionSummaryDto summaryDto);
}