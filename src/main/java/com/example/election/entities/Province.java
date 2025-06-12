package com.example.election.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "province")
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "province_id")
    private Integer provinceId;

    @Column(name = "province_name", unique = true, nullable = false)
    private String provinceName;
}