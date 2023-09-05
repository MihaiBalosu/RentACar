package com.diploma.rentacar.repository;

import com.diploma.rentacar.entity.Car;
import com.diploma.rentacar.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
}
