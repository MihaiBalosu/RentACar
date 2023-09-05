package com.diploma.rentacar.entity;

import com.diploma.rentacar.enums.CarTypeEnum;
import javax.persistence.*;

import com.diploma.rentacar.enums.FuelTypeEnum;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "price")
    private Double price;

    @Column(name = "number_of_passengers")
    private int numberOfPassengers;

    @Column(name = "number_of_bags")
    private int numberOfBags;

    @Column(name = "minimum_age_without_fee")
    private int minimumAgeWithoutFee;

    @Column(name = "name")
    private String name;

    @Column(name = "automaker")
    private String automaker;

    @Column(name = "photo_path", columnDefinition = "TEXT")
    private String photoPath;

    @Column(name = "automatic")
    private boolean automatic;

    @Column(name = "discount_applicable")
    private boolean discountApplicable;

    @Column(name = "car_type")
    @Enumerated(EnumType.STRING)
    private CarTypeEnum carType;

    @Column(name = "fuel_type")
    @Enumerated(EnumType.STRING)
    private FuelTypeEnum fuelType;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "car_discount",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "discount_id"))
    private List<Discount> discount;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rental> rentals;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_dealer_id")
    private CarDealer carDealer;

}
