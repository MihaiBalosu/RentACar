package com.diploma.rentacar.service;

import com.diploma.rentacar.dto.CarDealerDto;
import com.diploma.rentacar.entity.CarDealer;
import com.diploma.rentacar.repository.CarDealerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarDealerService {

  private CarDealerRepository carDealerRepository;

  @Autowired
    public CarDealerService(CarDealerRepository carDealerRepository) {
        this.carDealerRepository = carDealerRepository;
    }

    public CarDealer findByName(String name){
      return carDealerRepository.findByName(name);
    }

  public List<String> getDealerNames() {
    List<CarDealer> carDealers = carDealerRepository.findAll();

    return carDealers.stream().map(carDealer -> carDealer.getName()).collect(Collectors.toList());
  }

  public List<CarDealer> findAll(){
    return carDealerRepository.findAll();
  }

  public CarDealer save(CarDealer carDealer){
    return carDealerRepository.save(carDealer);
  }

  public void remove(long id){
    carDealerRepository.deleteById(id);
  }

    public CarDealer findById(Long carDealerId) {
      return carDealerRepository.findById(carDealerId).orElse(null);
    }
}
