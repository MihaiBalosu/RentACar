package com.diploma.rentacar.controller;


import com.diploma.rentacar.dto.AddOnDto;
import com.diploma.rentacar.entity.AddOn;
import com.diploma.rentacar.service.AddOnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/add-on")
public class AddOnController {

    private final AddOnService addOnService;

    @Autowired
    public AddOnController(AddOnService addOnService) {
        this.addOnService = addOnService;
    }

    @GetMapping
    public List<AddOnDto> findAll() {
        List<AddOn> addOns = addOnService.findAll();

        return  addOns.stream().map(addOn -> AddOnDto.fromEntity(addOn)).collect(Collectors.toList());
    }

    @DeleteMapping("{addOnId}")
    public void deleteAddOnById(@PathVariable Long addOnId) {
        addOnService.remove(addOnId);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public AddOnDto create(@Valid @RequestBody AddOnDto addOnDto) {
        AddOn addOn = addOnService.createOrUpdate(AddOnDto.toEntity(addOnDto));

        return AddOnDto.fromEntity(addOn);
    }
}
