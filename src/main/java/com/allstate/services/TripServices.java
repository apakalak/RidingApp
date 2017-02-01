package com.allstate.services;


import com.allstate.entities.*;
import com.allstate.enums.CarType;
import com.allstate.repositories.ITripRepository;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TripServices {
    @Autowired
    private ITripRepository tripRepository;

    @Autowired
    private CarServices carServices;

    public Trip create(Trip trip) {
       return this.tripRepository.save(trip);
    }

    public Trip create(Date tripStartTime, City city, Car car, double roughDistance , Passanger passanger) {

        double roughCost = this.carServices.findCostForGivenDistance(tripStartTime , city, car.getType(),roughDistance);

        if(roughCost <= passanger.getBalance()) {
            Trip trip =  new Trip();
            trip.setStartTime(tripStartTime);
            trip.setStopTime(new Date());
            trip.setPassangerReview("");
            trip.setDriverReview("");
            trip.setCar(car);
            trip.setPassanger(passanger);
            return this.tripRepository.save(trip);
        }
        else {
            throw new DataException("No Enough Balance for the distance and Car selected",null);
        }
    }

    public Trip createTrip(Date currentTime, String city, CarType car_type, int roughDistance, Passanger currentPassanger) {
        try {
            Car selectedCar = this.carServices.findFirstOfAvailableCars(city, car_type);
            if(selectedCar!=null){
                return this.create(currentTime, selectedCar.getDriver().getCity(),
                        selectedCar, roughDistance, currentPassanger);

            }else{
                throw new DataException("No car of selected type available at this point!",null);
            }
        }
        catch(DataException ex){
            throw new DataException(ex.getMessage(),ex.getSQLException());
        }
    }

    public Car findCarTakenDuringTrip(int id ) { return this.tripRepository.findOne(id).getCar() ; }

    public Driver findDriverTakenDuringTrip(int id) { return this.tripRepository.findOne(id).getCar().getDriver() ; }

    public Passanger findPassangerTakenDuringTrip(int id) { return this.tripRepository.findOne(id).getPassanger() ; }

    public List<Passanger> findAllPassangerTakenTripInACity(int id) { return  this.tripRepository.findAllPassangerTakenTripInACity(id) ; }

    public List<Driver> findAllDriversForAGivenPassanger(int id) { return this.tripRepository.findAllDriversForAGivenPassanger(id) ; }

    public double findTotalExpenditureByAPassangerOnTrips(int id) { return this.tripRepository.findTotalExpenditureByAPassangerOnTrips(id) ; }

    public double findTotalExpenditureByAPassangerOnTripsOnPastDay(int id) { return this.tripRepository.findTotalExpenditureByAPassangerOnTripsOnPastDay(id) ; }
}
