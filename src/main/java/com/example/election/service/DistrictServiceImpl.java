package com.example.election.service;

import com.example.election.dto.DistrictDto;
import com.example.election.entities.District;
import com.example.election.entities.Province;
import com.example.election.repository.DistrictRepository;
import com.example.election.repository.ProvinceRepository;
import com.example.election.service.DistrictService;
import com.example.election.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Override
    public StandardResponse createDistrict(DistrictDto districtDto) {
        try {
            Province province = provinceRepository.findById(districtDto.getProvinceId())
                    .orElseThrow(() -> new RuntimeException("Province not found"));

            District district = new District();
            district.setProvince(province);
            district.setDistrictName(districtDto.getDistrictName());
            district.setSeatCount(districtDto.getSeatCount());

            districtRepository.save(district);
            return new StandardResponse("SUCCESS", "District created successfully", district, HttpStatus.CREATED.value());
        } catch (Exception e) {
            return new StandardResponse("ERROR", e.getMessage(), null, HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public StandardResponse getDistrictsByProvince(Integer provinceId) {
        try {
            List<District> districts = districtRepository.findByProvinceProvinceId(provinceId);
            List<DistrictDto> districtDtos = districts.stream()
                    .map(d -> new DistrictDto(d.getDistrictId(), d.getProvince().getProvinceId(),
                            d.getDistrictName(), d.getSeatCount()))
                    .collect(Collectors.toList());
            return new StandardResponse("SUCCESS", "Districts retrieved successfully", districtDtos, HttpStatus.OK.value());
        } catch (Exception e) {
            return new StandardResponse("ERROR", e.getMessage(), null, HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public StandardResponse getDistrictCount() {
        try {
            long count = districtRepository.count();
            return new StandardResponse("SUCCESS", "District count retrieved", count, HttpStatus.OK.value());
        } catch (Exception e) {
            return new StandardResponse("ERROR", e.getMessage(), null, HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public StandardResponse getTotalSeatCount() {
        try {
            Integer totalSeats = districtRepository.sumSeatCount();
            return new StandardResponse("SUCCESS", "Total seat count retrieved", totalSeats, HttpStatus.OK.value());
        } catch (Exception e) {
            return new StandardResponse("ERROR", e.getMessage(), null, HttpStatus.BAD_REQUEST.value());
        }
    }
}