package com.diploma.rentacar.repository;

import com.diploma.rentacar.entity.AddOn;
import com.diploma.rentacar.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddOnRepository extends JpaRepository<AddOn, Long> {
}
