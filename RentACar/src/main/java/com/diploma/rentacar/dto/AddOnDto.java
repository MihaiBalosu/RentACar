package com.diploma.rentacar.dto;

import com.diploma.rentacar.entity.AddOn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddOnDto {

    private Long id;

    private String name;

    private String description;

    private Double price;

    public static AddOn toEntity(AddOnDto addOn) {
        return AddOn.builder()
                .id(addOn.getId())
                .name(addOn.getName())
                .description(addOn.getDescription())
                .price(addOn.getPrice())
                .build();
    }

    public static AddOnDto fromEntity(AddOn addOn) {
        return AddOnDto.builder()
                .id(addOn.getId())
                .name(addOn.getName())
                .description(addOn.getDescription())
                .price(addOn.getPrice())
                .build();
    }
}
