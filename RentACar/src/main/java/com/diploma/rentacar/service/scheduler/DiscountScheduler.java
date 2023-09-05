package com.diploma.rentacar.service.scheduler;

import com.diploma.rentacar.entity.Car;
import com.diploma.rentacar.entity.Rental;
import com.diploma.rentacar.enums.DiscountTypeEnum;
import com.diploma.rentacar.service.CarService;
import com.diploma.rentacar.service.DiscountService;
import com.diploma.rentacar.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DiscountScheduler {

    private final CarService carService;
    private final RentalService rentalService;
    private final DiscountService discountService;

    @Autowired
    public DiscountScheduler(CarService carService,
                             RentalService rentalService,
                             DiscountService discountService) {
        this.carService = carService;
        this.rentalService = rentalService;
        this.discountService = discountService;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void addDiscountToCars() {
        List<Rental> rentals = rentalService.findAll();
        List<Car> carsWithLastRentalOlderThanOneMonth = new ArrayList<>();

        rentals.forEach(rental -> {
            if (rental.getEndDate().isBefore(LocalDate.now().minusMonths(1))) {
                carsWithLastRentalOlderThanOneMonth.add(rental.getCar());
            }
        });

        carsWithLastRentalOlderThanOneMonth.forEach(car -> {
            car.getDiscount().add(discountService.generateDiscount(DiscountTypeEnum.LAST_RENTAL_TOO_OLD));
            carService.save(car);
        });
    }
}
