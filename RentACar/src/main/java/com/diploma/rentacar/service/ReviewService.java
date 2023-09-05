package com.diploma.rentacar.service;

import com.diploma.rentacar.entity.Car;
import com.diploma.rentacar.entity.Review;
import com.diploma.rentacar.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    private final CarService carService;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, CarService carService) {
        this.reviewRepository = reviewRepository;
        this.carService = carService;
    }

    public List<Review> findAll(){
        return reviewRepository.findAll();
    }

    public Review create(Review review, long carId){
        Car car = carService.find(carId);
        review = reviewRepository.save(review);

        car.getReviews().add(review);
        carService.save(car);

        return review;
    }

    public void remove(long id){
        reviewRepository.deleteById(id);
    }
}
