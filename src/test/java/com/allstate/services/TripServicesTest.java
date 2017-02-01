package com.allstate.services;

import com.allstate.entities.Car;
import com.allstate.entities.Driver;
import com.allstate.entities.Passanger;
import com.allstate.entities.Trip;
import com.allstate.enums.CarType;
import org.hibernate.exception.DataException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(value = "/sql/seed.sql")
public class TripServicesTest {

    @Autowired
    private  TripServices tripServices;

    @Autowired
    private PassangerServices passangerServices;

    @Test
    public void shouldCreateTripIfCarFoundAndBalanceAvailable() throws  Exception{
        String city = "bengluru";
        CarType car_type = CarType.BASIC;
        int roughDistance = 5; // in km
        Date currentTime = new Date();
        Passanger currentPassanger = this.passangerServices.findById(2);

        Trip trip = this.tripServices.createTrip(currentTime, city, car_type, roughDistance, currentPassanger);

        assertEquals(3, trip.getId());
        assertEquals("SWIFT", trip.getCar().getModel());
        assertEquals("Abhi", trip.getPassanger().getName());
        assertEquals(0, trip.getVersion());

    }

    @Test(expected = DataException.class)
    public void shouldNotCreateTripIfCarFound() throws  Exception{
        String city = "kolar";
        CarType car_type = CarType.BASIC;
        int roughDistance = 20; // in km
        Date currentTime = new Date();
        Passanger currentPassanger = this.passangerServices.findById(2);

        this.tripServices.createTrip(currentTime, city, car_type, roughDistance, currentPassanger);

    }

    @Test(expected = DataException.class)
    public void shouldNotCreateTripIfNoBalance() throws  Exception{
        String city = "bengluru";
        CarType car_type = CarType.BASIC;
        int roughDistance = 5000; // in km
        Date currentTime = new Date();
        Passanger currentPassanger = this.passangerServices.findById(2);

        this.tripServices.createTrip(currentTime, city, car_type, roughDistance, currentPassanger);
    }

    @Test
    public void findCarTakenDuringTrip() throws  Exception {
        Car car = this.tripServices.findCarTakenDuringTrip(2);
        assertEquals("zen", car.getModel());
    }

    @Test
    public void findDriverTakenDuringTrip() throws  Exception {
        Driver driver = this.tripServices.findDriverTakenDuringTrip(2);
        assertEquals("sudhir", driver.getName());
    }

    @Test
    public void findPassangerTakenDuringTrip() throws  Exception {
        Passanger passanger = this.tripServices.findPassangerTakenDuringTrip(2);
        assertEquals("Abhi", passanger.getName());
    }

    @Test
    public void findAllPassangerTakenTripInACity() throws  Exception {
        List<Passanger> listPassangers = this.tripServices.findAllPassangerTakenTripInACity(2);
        assertEquals(1, listPassangers.size());
        assertEquals("Abhi", listPassangers.get(0).getName());
    }

    @Test
    public void findAllDriversForAGivenPassanger() throws  Exception {
        List<Driver> drivers = this.tripServices.findAllDriversForAGivenPassanger(2);
        assertEquals(1, drivers.size());
        assertEquals("sudhir", drivers.get(0).getName());
    }

    @Test
    public void findTotalExpenditureByAPassangerOnTrips() throws  Exception {
        double amount = this.tripServices.findTotalExpenditureByAPassangerOnTrips(2);
        assertEquals(300, amount, 0.1);
    }

    @Test
    public void findTotalExpenditureByAPassangerOnTripsOnPastDay() throws  Exception {
        double amount = this.tripServices.findTotalExpenditureByAPassangerOnTripsOnPastDay(2);
        assertEquals(300, amount, 0.1);
    }
}