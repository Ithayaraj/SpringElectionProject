package com.example.election.repository;

import com.example.election.entities.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {
    List<District> findByProvinceProvinceId(Integer provinceId);
    long count();
    @Query("SELECT SUM(d.seatCount) FROM District d")
    Integer sumSeatCount();
}