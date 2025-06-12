package com.example.election.service;

import com.example.election.dto.SeatAllocationDto;
import com.example.election.utils.StandardResponse;

public interface SeatAllocationService {
    StandardResponse createSeatAllocation(SeatAllocationDto seatAllocationDto);
}