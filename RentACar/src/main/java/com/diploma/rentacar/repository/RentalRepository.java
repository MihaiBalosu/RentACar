package com.diploma.rentacar.repository;

import com.diploma.rentacar.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findByStartDateBetween(LocalDate startDate, LocalDate endDate);

}
