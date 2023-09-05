package com.diploma.rentacar.repository;

import com.diploma.rentacar.entity.CarDealer;
import com.diploma.rentacar.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarDealerRepository extends JpaRepository<CarDealer, Long> {

    CarDealer findByName(String name);

}
