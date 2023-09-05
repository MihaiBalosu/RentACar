package com.diploma.rentacar.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactDetailsDto {

    private Long id;

    private Long phoneNumber;

    private String countryCode;

    private String email;
}
