package com.diploma.rentacar.service;

import com.diploma.rentacar.dto.AvailableCarsDto;
import com.diploma.rentacar.dto.CarDto;
import com.diploma.rentacar.dto.ReviewDto;
import com.diploma.rentacar.entity.*;
import com.diploma.rentacar.repository.CarRepository;
import org.apache.catalina.valves.rewrite.RewriteValve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final CarDealerService carDealerService;

    @Autowired
    public CarService(CarRepository carRepository,
                      CarDealerService carDealerService) {
        this.carRepository = carRepository;
        this.carDealerService = carDealerService;
    }

    @Transactional
    public Car find(Long id) {
        return carRepository.findById(id).orElse(null);
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }


    public Car create(CarDto carDto) {
        CarDealer carDealer = carDealerService.findByName(carDto.getCarDealer());
        Car car = Car.builder()
                .name(carDto.getName())
                .photoPath(carDto.getPhotoPath())
                .carType(carDto.getCarType())
                .automaker(carDto.getAutomaker())
                .automatic(carDto.isAutomatic())
                .fuelType(carDto.getFuelType())
                .minimumAgeWithoutFee(carDto.getMinimumAgeWithoutFee())
                .numberOfBags(carDto.getNumberOfBags())
                .numberOfPassengers(carDto.getNumberOfPassengers())
                .price(carDto.getPrice())
                .carDealer(carDealer)
                .reviews(new ArrayList<>())
                .build();

        return carRepository.save(car);
    }

    public List<Car> getBestRatedCars() {
        List<Car> cars = findAll();

        cars.sort(Comparator.comparing(car -> calculateAverageRating(car.getReviews())));
        Collections.reverse(cars);
        return cars.subList(0, Math.min(10, cars.size()));
    }

    public BigDecimal calculateAverageRating(List<Review> reviews) {
        BigDecimal totalRating = BigDecimal.ZERO;

        for (Review review : reviews) {
            totalRating = totalRating.add(review.getRating());
        }

        int numReviews = reviews.size();
        if (numReviews > 0) {
            return totalRating.divide(BigDecimal.valueOf(numReviews), 2, RoundingMode.HALF_UP);
        } else {
            return BigDecimal.ZERO;
        }
    }

    public void delete(Long carId) {
        carRepository.deleteById(carId);
    }

    // Method to get available cars at a location for a given time duration
    public List<Car> getAvailableCarsAtLocation(Long carDealerId, LocalDate startTime, LocalDate endTime) {
        CarDealer carDealer = carDealerService.findById(carDealerId);
        return carDealer.getCars().stream()
                .filter(car -> isCarAvailableForDuration(car, startTime, endTime))
                .collect(Collectors.toList());
    }

    // Method to check if a car is available for the entire duration
    public boolean isCarAvailableForDuration(Car car, LocalDate startTime, LocalDate endTime) {
        List<Rental> rentals = car.getRentals();
        for (Rental rental : rentals) {
            if (rental.getStartDate().isBefore(endTime) && rental.getEndDate().isAfter(startTime)) {
                return false; // Car is not available for the entire duration
            }
        }
        return true; // Car is available
    }

    public void save(Car car) {
        carRepository.save(car);
    }

    public List<LocalDate> getUnavailableDates(Long carId) {
        Car car = carRepository.getOne(carId);
        List<LocalDate> unavailableDates = new ArrayList<>();

        car.getRentals().forEach(rental -> unavailableDates.addAll(getUnavailableDates(rental.getStartDate(),
                rental.getEndDate()))
        );

        return unavailableDates;
    }

    private Collection<LocalDate> getUnavailableDates(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> datesInRange = new ArrayList<>();
        LocalDate currentDate = startDate;

        while (!currentDate.isAfter(endDate)) {
            datesInRange.add(currentDate);
            currentDate = currentDate.plusDays(1);
        }

        return datesInRange;
    }

    public List<Car> getAvailableCarsForDates(AvailableCarsDto availableCarsDto) {
        List<Car> cars = carRepository.findAll();
        return cars.stream()
                .filter(car -> isCarAvailableForDuration(car, availableCarsDto.getStartDate(), availableCarsDto.getEndDate()))
                .collect(Collectors.toList());

    }

    public boolean isCarAvailableForDuration(AvailableCarsDto availableCarsDto) throws Exception {
        Car car = carRepository.findById(availableCarsDto.getCarId()).orElse(null);
        if(car != null){
            return isCarAvailableForDuration(car, availableCarsDto.getStartDate(), availableCarsDto.getEndDate());
        }

        throw new Exception("Car does not exist");
    }

    @Transactional
    public void addReview(ReviewDto reviewDto) {
        Review review = ReviewDto.toEntity(reviewDto);
        review.setReviewDate(LocalDate.now());
        Car car = carRepository.findById(reviewDto.getCarId()).orElse(null);
        if(car != null){
            car.getReviews().add(review);
            carRepository.save(car);
        }
    }

    @Transactional
    public List<Review> getReviews(long carId) {
        Car car = carRepository.findById(carId).orElse(null);
        if(car != null){
            return car.getReviews();
        }
        return new ArrayList<>();
    }
}
