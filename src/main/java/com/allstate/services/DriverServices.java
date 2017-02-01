package com.allstate.services;


import com.allstate.entities.Car;
import com.allstate.entities.Driver;
import com.allstate.entities.Trip;
import com.allstate.repositories.IDriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DriverServices {

    @Autowired
    private IDriverRepository driverRepository;

    public Driver create(Driver driver) {
        return this.driverRepository.save(driver);
    }

    public Driver findById(int id) {
        return this.driverRepository.findOne(id);
    }

    public void deleteById(int id) { this.driverRepository.delete(id); }

    @Transactional
    public void addVoilation(int id) {
        Driver driver = this.driverRepository.findOne(id);
        if(driver != null){
            int voilations = driver.getNo_of_voilation();
            voilations = voilations + 1;
            driver.setNo_of_voilation(voilations);
            this.driverRepository.save(driver);
        }
    }

    @Transactional
    public List<Car> findAllTheCarsOwnByDriver(int id) {  return this.driverRepository.findOne(id).getCars(); }

    public List<Trip> findAllTripsForGivenDriver(int id) { return this.driverRepository.findAllTripsForGivenDriver(id) ; }
}
