package com.diploma.rentacar.controller;

import com.diploma.rentacar.dto.*;
import com.diploma.rentacar.entity.Car;
import com.diploma.rentacar.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/car")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<CarDto> findAll() {
        List<Car> cars = carService.findAll();

        return  cars.stream().map(car -> CarDto.fromEntity(car)).collect(Collectors.toList());
    }

    @GetMapping(value = "{id}", produces = "application/json")
    public CarDto findOne(@PathVariable Long id) {
        return CarDto.fromEntity(carService.find(id));
    }

    @GetMapping("/best-rated-cars")
    public List<CarDto> getBestRatedCars() {
        List<Car> cars = carService.getBestRatedCars();

        return  cars.stream().map(car -> CarDto.fromEntity(car)).collect(Collectors.toList());
    }

    @GetMapping("/unavailable-dates/{carId}")
    public List<LocalDate> getUnavailableDates(@PathVariable Long carId) {
        return carService.getUnavailableDates(carId);
    }

    @DeleteMapping("{carId}")
    public void deleteCarById(@PathVariable Long carId) {
        carService.delete(carId);
    }

    @PostMapping
//    @PreAuthorize("hasRole('CAR_DEALER')")
    @ResponseStatus(code = HttpStatus.CREATED)
    public CarDto create(@Valid @RequestBody CarDto carDto) {
        Car car = carService.create(carDto);

        return CarDto.fromEntity(car);
    }

    @PostMapping("/available-cars")
    public List<CarDto> getAvailableCars(@RequestBody Long carDealerId, LocalDate startTime, LocalDate endTime) {
        List<Car> availableCars = carService.getAvailableCarsAtLocation(carDealerId, startTime, endTime);

        return availableCars.stream().map(availableCar -> CarDto.fromEntity(availableCar)).collect(Collectors.toList());
    }

    @PostMapping("/available-cars-for-dates")
    public List<CarDto> getAvailableCarsForDates(@RequestBody AvailableCarsDto availableCarsDto) {
        List<Car> availableCars = carService.getAvailableCarsForDates(availableCarsDto);

        return availableCars.stream().map(availableCar -> CarDto.fromEntity(availableCar)).collect(Collectors.toList());
    }

    @PostMapping("/is-car-available")
    public ResponseEntity<Boolean> isCarAvailableForDuration(@RequestBody AvailableCarsDto availableCarsDto) {
        boolean isCarAvailable = false;
        try {
            isCarAvailable = carService.isCarAvailableForDuration(availableCarsDto);
            return ResponseEntity.ok(isCarAvailable);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/car-review")
    public ResponseEntity<List<ReviewDto>> addReview(@RequestBody ReviewDto reviewDto) {
        carService.addReview(reviewDto);

        List<ReviewDto> reviews = carService.getReviews(reviewDto.getCarId())
                .stream()
                .map(review -> ReviewDto.fromEntity(review)).collect(Collectors.toList());
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/car-review/{carId}")
    public ResponseEntity<List<ReviewDto>> getReviews(@PathVariable Long carId) {
        List<ReviewDto> reviews = carService.getReviews(carId)
                .stream()
                .map(review -> ReviewDto.fromEntity(review)).collect(Collectors.toList());
        return ResponseEntity.ok(reviews);
    }
}
