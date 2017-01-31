package com.allstate.services;


import com.allstate.entities.Car;
import com.allstate.repositories.ICarRepository;
import com.allstate.repositories.ICityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class CarServices  {

    @Autowired
    private ICarRepository carRepo;

    public Car create(Car car) {
       return this.carRepo.save(car);
    }

    public Car findById(int id) { return this.carRepo.findOne(id); }

    public void deleteById(int id) { this.carRepo.delete(id); }


}
