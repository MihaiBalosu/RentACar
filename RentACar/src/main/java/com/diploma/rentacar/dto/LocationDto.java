package com.diploma.rentacar.dto;

import com.diploma.rentacar.entity.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto {

    private Long id;

    private String country;

    private String city;

    private String street;

    private String streetNumber;

    private String postalCode;

    private float latitude;

    private float longitude;

    public static LocationDto fromEntity(Location location) {
        return LocationDto.builder()
                .id(location.getId())
                .country(location.getCountry())
                .city(location.getCity())
                .street(location.getStreet())
                .streetNumber(location.getStreetNumber())
                .postalCode(location.getPostalCode())
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .build();
    }

    public static Location toEntity(LocationDto location) {
        return Location.builder()
                .id(location.getId())
                .country(location.getCountry())
                .city(location.getCity())
                .street(location.getStreet())
                .streetNumber(location.getStreetNumber())
                .postalCode(location.getPostalCode())
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .build();
    }
}
