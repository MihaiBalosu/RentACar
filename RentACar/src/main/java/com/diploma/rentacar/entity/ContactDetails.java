package com.diploma.rentacar.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Data
@Entity(name = "contact_details")
public class ContactDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "phone_number")
    private Long phoneNumber;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "email")
    @Email
    private String email;
}
