package com.diploma.rentacar.entity;

import javax.persistence.*;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "rating")
    private BigDecimal rating;

    @Column(name = "review", columnDefinition = "text")
    private String review;

    @Column(name = "review_date")
    private LocalDate reviewDate;
}
