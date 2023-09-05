package com.diploma.rentacar.service;

import com.diploma.rentacar.entity.*;
import com.diploma.rentacar.repository.RentalRepository;
import com.diploma.rentacar.service.email.EmailPublisher;
import com.diploma.rentacar.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;

    private final CarService carService;

    private final EmailService emailService;

    private final EmailPublisher emailPublisher;

    @Autowired
    public RentalService(RentalRepository rentalRepository,
                         CarService carService,
                         EmailService emailService,
                         EmailPublisher emailPublisher) {
        this.rentalRepository = rentalRepository;
        this.carService = carService;
        this.emailService = emailService;
        this.emailPublisher = emailPublisher;
    }

    public boolean isCarAvailable(Car car, LocalDate startTime, LocalDate endTime){
        return carService.isCarAvailableForDuration(car, startTime, endTime);
    }

    public List<Rental> findAll() {
        return  rentalRepository.findAll();
    }

    public List<Rental> getRentalsStartingNextDay() {
        LocalDate nextDayStart = LocalDate.now().plusDays(1);
        LocalDate nextDayEnd = LocalDate.now().plusDays(1);
        return rentalRepository.findByStartDateBetween(nextDayStart, nextDayEnd);
    }

    @Transactional
    public Rental save(Rental rental) {
        return rentalRepository.save(rental);
    }

    public Rental create(Rental rental) {
        if(carService.isCarAvailableForDuration(rental.getCar(), rental.getStartDate(), rental.getEndDate())){
            return save(rental);
        }
        return null;
    }

    @Transactional
    public void remove(Long rentalId) {
        rentalRepository.deleteById(rentalId);
    }

    public void sendConfirmationEmail(long rentalId) {
        Rental rental = rentalRepository.getOne(rentalId);
        emailService.generateEmailProperties(rental, "rent-confirmation-email.vm",
                "rent-a-car@applciation.com",
                "Rent Confirmation");
    }
}
