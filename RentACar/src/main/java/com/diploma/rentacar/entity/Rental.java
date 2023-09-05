package com.diploma.rentacar.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "rental")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "start_time")
    private LocalDate startDate;

    @Column(name = "end_time")
    private LocalDate endDate;

    @Column(name = "price")
    private Double price;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;

    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<AddOn> addOns;

    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<CarProtection> carProtections;

}
