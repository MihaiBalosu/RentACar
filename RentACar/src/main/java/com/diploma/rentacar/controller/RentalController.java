package com.diploma.rentacar.controller;

import com.diploma.rentacar.dto.RentalDto;
import com.diploma.rentacar.entity.Rental;
import com.diploma.rentacar.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/api/booking")
public class RentalController {

    private final RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

//    @GetMapping
//    public List<RentalDto> findAll() {
//        List<Rental> carDealers = rentalService.findAll();
//
//        return  carDealers.stream().map(carDealer -> RentalDto.fromEntity(carDealer)).collect(Collectors.toList());
//    }
//
//    @DeleteMapping("{bookindID}")
//    public void deleteBookingCarById(@PathVariable Long rentalId) {
//        rentalService.remove(rentalId);
//    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public RentalDto create(@RequestBody RentalDto rentalDto) {
        Rental rental = rentalService.save(RentalDto.toEntity(rentalDto));
//        rentalService.sendConfirmationEmail(rental.getId());

        return RentalDto.fromEntity(rental);
    }
}
