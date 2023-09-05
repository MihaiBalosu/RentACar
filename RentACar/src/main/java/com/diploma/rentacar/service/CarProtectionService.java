package com.diploma.rentacar.service;

import com.diploma.rentacar.entity.CarProtection;
import com.diploma.rentacar.repository.CarProtectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarProtectionService {

    private final CarProtectionRepository carProtectionRepository;

    @Autowired
    public CarProtectionService(CarProtectionRepository carProtectionRepository) {
        this.carProtectionRepository = carProtectionRepository;
    }


    public List<CarProtection> findAll(){
        return carProtectionRepository.findAll();
    }

    public CarProtection createOrUpdate(CarProtection carProtection){

        return carProtectionRepository.save(carProtection);
    }

    public void remove(long id){
        carProtectionRepository.deleteById(id);
    }
}
