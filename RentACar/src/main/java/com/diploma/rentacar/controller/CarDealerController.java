package com.diploma.rentacar.controller;

import com.diploma.rentacar.dto.CarDealerDto;
import com.diploma.rentacar.dto.CarDto;
import com.diploma.rentacar.entity.Car;
import com.diploma.rentacar.entity.CarDealer;
import com.diploma.rentacar.service.CarDealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/car-dealer")
public class CarDealerController {

    private final CarDealerService carDealerService;

    @Autowired
    public CarDealerController(CarDealerService carDealerService) {
        this.carDealerService = carDealerService;
    }

    @GetMapping("/name")
    public List<String> getDealerNames() {
        return carDealerService.getDealerNames();
    }

    @GetMapping
    public List<CarDealerDto> findAll() {
        List<CarDealer> carDealers = carDealerService.findAll();

        return  carDealers.stream().map(carDealer -> CarDealerDto.fromEntity(carDealer)).collect(Collectors.toList());
    }

    @DeleteMapping("{carDealerId}")
    public void deleteCarById(@PathVariable Long carDealerId) {
        carDealerService.remove(carDealerId);
    }

    @PostMapping
//    @PreAuthorize("hasRole('CAR_DEALER')")
    @ResponseStatus(code = HttpStatus.CREATED)
    public CarDealerDto create(@Valid @RequestBody CarDealerDto carDto) {
        CarDealer carDealer = carDealerService.save(CarDealerDto.toEntity(carDto));

        return CarDealerDto.fromEntity(carDealer);
    }
}
