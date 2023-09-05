package com.diploma.rentacar.dto;


import com.diploma.rentacar.entity.ContactDetails;
import com.diploma.rentacar.entity.Driver;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DriverDto {

    private Long id;

    private String firstname;

    private String lastname;

    private String company;

    private LocalDate birthDate;

    public static DriverDto fromEntity(Driver driver) {
        return DriverDto.builder()
                .id(driver.getId())
                .firstname(driver.getFirstname())
                .birthDate(driver.getBirthDate())
                .build();
    }

    public static Driver toEntity(DriverDto driver) {
        return Driver.builder()
                .id(driver.getId())
                .firstname(driver.getFirstname())
                .birthDate(driver.getBirthDate())
                .build();
    }
}
