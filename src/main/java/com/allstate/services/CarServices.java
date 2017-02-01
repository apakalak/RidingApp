package com.allstate.services;


import com.allstate.entities.Car;
import com.allstate.entities.City;
import com.allstate.entities.Driver;
import com.allstate.entities.Trip;
import com.allstate.enums.CarType;
import com.allstate.repositories.ICarRepository;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Service
public class CarServices  {

    @Autowired
    private ICarRepository carRepo;

    public Car create(Car car) {
       return this.carRepo.save(car);
    }

    public Car findById(int id) { return this.carRepo.findOne(id); }

    public void deleteById(int id) { this.carRepo.delete(id); }

    public Car findFirstOfAvailableCars(String city, CarType car_type) {
        List<Car> cars = this.carRepo.findAvailableCars(city,car_type);
        if(cars != null && cars.size() > 0){
            return cars.get(0);
        }
        throw new DataException("No Cars found!!",null);
    }

    public double findCostForGivenDistance(Date tripStartTime, City city, CarType type, double distance){
        // id tripStartTIme > 10 PM and < 6 AM the get nightTimeCharge from city . else get dayTimeCharge
        // for given roughDistance, calculate the total Cost with lux tax if applicable and return
        LocalTime after = LocalTime.parse("22:00:00");
        LocalTime before = LocalTime.parse("05:00:00");
        LocalTime now = LocalTime.of(tripStartTime.getHours(), tripStartTime.getMinutes());
        double charges = 0;
        double luxTax = 0;
        if(now.isAfter(after) && now.isBefore(before)){
            charges = city.getNight_charge();
        }
        else {
            charges = city.getDay_charge();
        }
        if(type == CarType.LUX){
            luxTax = city.getLux_tax();
        }
        double cost = distance * charges;
        return cost + (cost * luxTax/100);
    }

    public Driver findDriverForGivenCar(int id) { return this.carRepo.findOne(id).getDriver(); }

    public List<Trip> findAllTripsForGivenCar(int id) { return  this.carRepo.findAllTripsForGivenCar(id); }

}
