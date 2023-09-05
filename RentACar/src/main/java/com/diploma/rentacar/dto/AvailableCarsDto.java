package com.diploma.rentacar.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AvailableCarsDto {

    private Long carId;
    private LocalDate startDate;
    private LocalDate endDate;
}
