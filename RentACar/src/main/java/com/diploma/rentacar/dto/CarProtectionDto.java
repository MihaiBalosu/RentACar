package com.diploma.rentacar.dto;

import com.diploma.rentacar.entity.CarProtection;
import com.diploma.rentacar.entity.Rental;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarProtectionDto {

    private Long id;

    private Double price;

    private String name;

    private String description;

    public static CarProtectionDto fromEntity(CarProtection carProtection) {
        return CarProtectionDto.builder()
                .id(carProtection.getId())
                .name(carProtection.getName())
                .description(carProtection.getDescription())
                .build();
    }

    public static CarProtection toEntity(CarProtectionDto carProtection) {
        return CarProtection.builder()
                .id(carProtection.getId())
                .name(carProtection.getName())
                .description(carProtection.getDescription())
                .build();
    }
}
