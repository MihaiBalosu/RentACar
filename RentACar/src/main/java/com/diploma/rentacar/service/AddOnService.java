package com.diploma.rentacar.service;

import com.diploma.rentacar.entity.AddOn;
import com.diploma.rentacar.repository.AddOnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddOnService {

    private final AddOnRepository addOnRepository;

    @Autowired
    public AddOnService(AddOnRepository addOnRepository) {
        this.addOnRepository = addOnRepository;
    }


    public List<AddOn> findAll(){
        return addOnRepository.findAll();
    }

    public AddOn createOrUpdate(AddOn addOn){
        return addOnRepository.save(addOn);
    }

    public void remove(long id){
        addOnRepository.deleteById(id);
    }
}
