package com.example.election.service;

import com.example.election.dto.ProvinceDto;
import com.example.election.utils.StandardResponse;

import java.util.List;

public interface ProvinceService {
    StandardResponse createProvince(ProvinceDto provinceDto);
    StandardResponse getAllProvinces();
    StandardResponse updateProvince(Integer id, ProvinceDto provinceDto);
    StandardResponse deleteProvince(Integer id);
    StandardResponse getProvinceCount();
}