package com.example.election.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistrictDto {
    private Integer districtId;
    private Integer provinceId;
    private String districtName;
    private Integer seatCount;

}