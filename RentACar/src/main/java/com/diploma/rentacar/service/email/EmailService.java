package com.diploma.rentacar.service.email;

import com.diploma.rentacar.dto.email.EmailPropertiesDto;
import com.diploma.rentacar.entity.Driver;
import com.diploma.rentacar.entity.Location;
import com.diploma.rentacar.entity.Rental;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static com.diploma.rentacar.enums.EmailPlaceholdersEnum.*;

@Service
public class EmailService {
    public EmailPropertiesDto generateEmailProperties(Rental rental, String emailTemplate, String sender, String subject) {
        return EmailPropertiesDto.builder()
                .emailTemplate(emailTemplate)
                .from(sender)
                .to(rental.getDriver().getEmail())
                .subject(subject)
                .placeholders(generatePlaceholders(rental))
                .build();
    }

    private Map<String, String> generatePlaceholders(Rental rental) {
        Map<String, String> placeholders = new HashMap<>();
        placeholders.put(DRIVER_NAME.toString(), getDriverFullName(rental.getDriver()));
        placeholders.put(CAR_NAME.toString(), rental.getCar().getName());
        placeholders.put(START_TIME.toString(), rental.getStartDate().toString());
        placeholders.put(END_TIME.toString(), rental.getEndDate().toString());
        placeholders.put(LOCATION.toString(), generateFullLocation(rental.getCar().getCarDealer().getLocation()));

        return placeholders;

    }

    private String generateFullLocation(Location location) {
        return location.getStreet() + " "
                + location.getStreetNumber() + " "
                + location.getCity() + " "
                + location.getCountry();
    }

    private String getDriverFullName(Driver driver) {
        return driver.getFirstname() + " " + driver.getLastname();
    }
}
