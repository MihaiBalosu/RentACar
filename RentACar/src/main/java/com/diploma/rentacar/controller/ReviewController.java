package com.diploma.rentacar.controller;

import com.diploma.rentacar.dto.CarProtectionDto;
import com.diploma.rentacar.dto.ReviewDto;
import com.diploma.rentacar.entity.CarProtection;
import com.diploma.rentacar.entity.Review;
import com.diploma.rentacar.service.CarProtectionService;
import com.diploma.rentacar.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public List<ReviewDto> findAll() {
        List<Review> reviews = reviewService.findAll();

        return  reviews.stream().map(review -> ReviewDto.fromEntity(review)).collect(Collectors.toList());
    }

    @DeleteMapping("{reviewId}")
    public void deleteReviewById(@PathVariable Long reviewId) {
        reviewService.remove(reviewId);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ReviewDto create(@Valid @RequestBody ReviewDto reviewDto) {
        Review review = reviewService.create(ReviewDto.toEntity(reviewDto), reviewDto.getCarId());

        return ReviewDto.fromEntity(review);
    }
}
