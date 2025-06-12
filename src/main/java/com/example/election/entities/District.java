package com.example.election.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "district")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "district_id")
    private Integer districtId;

    @ManyToOne
    @JoinColumn(name = "province_id", nullable = false)
    private Province province;

    @Column(name = "district_name", unique = true, nullable = false)
    private String districtName;

    @Column(name = "seat_count", nullable = false)
    private Integer seatCount;
}