package com.diploma.rentacar.dto;

import com.diploma.rentacar.entity.Car;
import com.diploma.rentacar.entity.CarDealer;
import com.diploma.rentacar.entity.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarDealerDto {

    private Long id;

    private String name;

    private List<CarDto> cars;

    private LocationDto location;

    public static CarDealerDto fromEntity(CarDealer carDealer) {
        return CarDealerDto.builder()
                .id(carDealer.getId())
                .name(carDealer.getName())
                .cars(carDealer.getCars().stream().map(car -> CarDto.fromEntity(car)).collect(Collectors.toList()))
                .location(LocationDto.fromEntity(carDealer.getLocation()))
                .build();
    }

    public static CarDealer toEntity(CarDealerDto carDealer) {
        return CarDealer.builder()
                .id(carDealer.getId())
                .name(carDealer.getName())
                .cars(carDealer.getCars().stream().map(car -> CarDto.toEntity(car)).collect(Collectors.toList()))
                .location(LocationDto.toEntity(carDealer.getLocation()))
                .build();
    }
}
