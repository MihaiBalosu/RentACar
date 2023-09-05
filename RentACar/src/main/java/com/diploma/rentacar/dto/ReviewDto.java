package com.diploma.rentacar.dto;

import com.diploma.rentacar.entity.Car;
import com.diploma.rentacar.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDto {

    private Long id;

    private BigDecimal rating;

    private String review;

    private LocalDate reviewDate;

    private long carId;

    public static ReviewDto fromEntity(Review review) {
        return ReviewDto.builder()
                .id(review.getId())
                .rating(review.getRating())
                .review(review.getReview())
                .reviewDate(review.getReviewDate())
                .build();
    }

    public static Review toEntity(ReviewDto reviewDto) {
        return Review.builder()
                .rating(reviewDto.getRating())
                .review(reviewDto.getReview())
                .reviewDate(reviewDto.getReviewDate())
                .build();
    }
}
