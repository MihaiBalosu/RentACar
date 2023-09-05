package com.diploma.rentacar.controller;

import com.diploma.rentacar.dto.CarProtectionDto;
import com.diploma.rentacar.entity.CarProtection;
import com.diploma.rentacar.service.CarProtectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/car-protection")
public class CarProtectionController {

    private final CarProtectionService carProtectionService;

    @Autowired
    public CarProtectionController(CarProtectionService carProtectionService) {
        this.carProtectionService = carProtectionService;
    }

    @GetMapping
    public List<CarProtectionDto> findAll() {
        List<CarProtection> carProtectionDtos = carProtectionService.findAll();

        return  carProtectionDtos.stream().map(carProtectionDto -> CarProtectionDto.fromEntity(carProtectionDto)).collect(Collectors.toList());
    }

    @DeleteMapping("{carProtectionId}")
    public void deleteCarProtectionById(@PathVariable Long carProtectionId) {
        carProtectionService.remove(carProtectionId);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CarProtectionDto create(@Valid @RequestBody CarProtectionDto carProtectionDto) {
        CarProtection carProtection = carProtectionService.createOrUpdate(CarProtectionDto.toEntity(carProtectionDto));

        return CarProtectionDto.fromEntity(carProtection);
    }
}
