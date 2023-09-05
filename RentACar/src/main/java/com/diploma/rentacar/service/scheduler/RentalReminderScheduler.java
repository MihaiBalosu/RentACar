package com.diploma.rentacar.service.scheduler;

import com.diploma.rentacar.entity.Rental;
import com.diploma.rentacar.service.email.EmailPublisher;
import com.diploma.rentacar.service.RentalService;
import com.diploma.rentacar.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalReminderScheduler {

    private final RentalService rentalService;
    private final EmailPublisher emailPublisher;
    private final EmailService emailService;

    @Autowired
    public RentalReminderScheduler(RentalService rentalService,
                                   EmailPublisher emailPublisher,
                                   EmailService emailService) {
        this.rentalService = rentalService;
        this.emailPublisher = emailPublisher;
        this.emailService = emailService;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void sendRentalReminderEmail() {
        List<Rental> rentals = rentalService.getRentalsStartingNextDay();
        rentals.forEach(rental -> emailPublisher.publishEmail(
                emailService.generateEmailProperties(
                        rental,
                        "reminder-email.vm",
                        "rent_a_car@application.com",
                        "subject")
                )
        );

    }
}
