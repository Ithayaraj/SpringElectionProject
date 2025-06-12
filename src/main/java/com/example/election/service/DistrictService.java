package com.example.election.service;

import com.example.election.dto.DistrictDto;
import com.example.election.utils.StandardResponse;

public interface DistrictService {
    StandardResponse createDistrict(DistrictDto districtDto);
    StandardResponse getDistrictsByProvince(Integer provinceId);
    StandardResponse getDistrictCount();
    StandardResponse getTotalSeatCount();
}