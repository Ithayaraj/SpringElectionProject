package com.example.election.service;

import com.example.election.dto.ProvinceDto;
import com.example.election.entities.Province;
import com.example.election.repository.ProvinceRepository;
import com.example.election.service.ProvinceService;
import com.example.election.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;

    @Override
    public StandardResponse createProvince(ProvinceDto provinceDto) {
        try {
            if (provinceDto.getProvinceName() == null || provinceDto.getProvinceName().trim().isEmpty()) {
                return new StandardResponse("ERROR", "Province name cannot be null or empty", null, HttpStatus.BAD_REQUEST.value());
            }

            Province province = new Province();
            province.setProvinceName(provinceDto.getProvinceName().trim());

            provinceRepository.save(province);
            return new StandardResponse("SUCCESS", "Province created successfully", province, HttpStatus.CREATED.value());
        } catch (Exception e) {
            return new StandardResponse("ERROR", e.getMessage(), null, HttpStatus.BAD_REQUEST.value());
        }
    }


    @Override
    public StandardResponse getAllProvinces() {
        try {
            List<ProvinceDto> provinces = provinceRepository.findAll().stream()
                    .map(p -> new ProvinceDto(p.getProvinceId(), p.getProvinceName()))
                    .collect(Collectors.toList());
            return new StandardResponse("SUCCESS", "Provinces retrieved successfully", provinces, HttpStatus.OK.value());
        } catch (Exception e) {
            return new StandardResponse("ERROR", e.getMessage(), null, HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public StandardResponse updateProvince(Integer id, ProvinceDto provinceDto) {
        try {
            Province province = provinceRepository.findById(id).orElseThrow(() -> new RuntimeException("Province not found"));
            province.setProvinceName(provinceDto.getProvinceName());
            provinceRepository.save(province);
            return new StandardResponse("SUCCESS", "Province updated successfully", province, HttpStatus.OK.value());
        } catch (Exception e) {
            return new StandardResponse("ERROR", e.getMessage(), null, HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public StandardResponse deleteProvince(Integer id) {
        try {
            provinceRepository.deleteById(id);
            return new StandardResponse("SUCCESS", "Province deleted successfully", null, HttpStatus.OK.value());
        } catch (Exception e) {
            return new StandardResponse("ERROR", e.getMessage(), null, HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public StandardResponse getProvinceCount() {
        try {
            long count = provinceRepository.count();
            return new StandardResponse("SUCCESS", "Province count retrieved", count, HttpStatus.OK.value());
        } catch (Exception e) {
            return new StandardResponse("ERROR", e.getMessage(), null, HttpStatus.BAD_REQUEST.value());
        }
    }
}