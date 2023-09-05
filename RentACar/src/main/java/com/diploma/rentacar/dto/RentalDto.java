package com.diploma.rentacar.dto;


import com.diploma.rentacar.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentalDto {

    private Long id;

    private LocalDate startDate;

    private LocalDate endDate;

    private DriverDto driver;

    private List<AddOnDto> addOns;

    private List<CarProtectionDto> carProtections;

    private CarDto car;

    public static Rental toEntity(RentalDto rentalDto) {
        return Rental.builder()
                .id(rentalDto.getId())
                .startDate(rentalDto.getStartDate())
                .endDate(rentalDto.getEndDate())
                .driver(DriverDto.toEntity(rentalDto.getDriver()))
                .addOns(rentalDto.getAddOns().stream().map(addOn -> AddOnDto.toEntity(addOn)).collect(Collectors.toList()))
                .carProtections(rentalDto.getCarProtections().stream().map(carProtection -> CarProtectionDto.toEntity(carProtection)).collect(Collectors.toList()))
                .car(CarDto.toEntity(rentalDto.getCar()))
                .build();
    }

    public static RentalDto fromEntity(Rental rental) {
        return RentalDto.builder()
                .id(rental.getId())
                .startDate(rental.getStartDate())
                .endDate(rental.getEndDate())
                .driver(DriverDto.fromEntity(rental.getDriver()))
                .addOns(rental.getAddOns().stream().map(addOn -> AddOnDto.fromEntity(addOn)).collect(Collectors.toList()))
                .carProtections(rental.getCarProtections().stream().map(carProtection -> CarProtectionDto.fromEntity(carProtection)).collect(Collectors.toList()))
                .car(CarDto.fromEntity(rental.getCar()))
                .build();
    }
}
