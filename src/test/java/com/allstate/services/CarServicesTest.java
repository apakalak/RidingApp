package com.allstate.services;

import com.allstate.entities.Car;
import com.allstate.entities.Driver;
import com.allstate.entities.Trip;
import com.allstate.enums.CarType;
import org.hibernate.exception.DataException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(value = "/sql/seed.sql")
public class CarServicesTest {

    @Autowired
    private CarServices carServices;

    @Autowired
    private DriverServices driverServices;

    @Autowired
    private CityServices cityServices;

    @Test
    public void shouldCreateCar() throws Exception {

        Car car = new Car();
        car.setMake("Maruti");
        car.setModel("zen");
        car.setYear(1998);
        car.setType(CarType.BASIC);
        car.setDriver(this.driverServices.findById(4));
        Car result =this.carServices.create(car);

        assertEquals(5, result.getId());
        assertEquals("Maruti", result.getMake());
        assertEquals("krishna", result.getDriver().getName());

    }

    @Test
    public void shouldFindCarByID() throws Exception {
        Car result = this.carServices.findById(2);
        assertEquals("Maruthi", result.getMake());
        assertEquals("zen", result.getModel());

    }

    @Test(expected = DataIntegrityViolationException.class)
    public void shouldFindCarByIDAndThrowExceptionInCaseUsed() throws Exception {
        this.carServices.deleteById(2);
    }

    @Test
    public void shouldDeleteCarByIDIfExists() throws Exception {
        this.carServices.deleteById(4);
        Car result = this.carServices.findById(4);
        assertNull(result);

    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void shouldDeleteCarByIDIfDoesNotExists() throws Exception {
        this.carServices.deleteById(20);

    }

    @Test
    public void findFirstOfAvailableCarsIfCarPresent() throws Exception {
        Car car = this.carServices.findFirstOfAvailableCars("bengluru",CarType.BASIC);
        assertNotNull(car);
        assertEquals(3, car.getId());
    }

    @Test(expected = DataException.class)
    public void findFirstOfAvailableCarsIfCarNotPresent() throws Exception {
        this.carServices.findFirstOfAvailableCars("kolar",CarType.BASIC);
    }

    @Test
    public void findCostForGivenDistance() throws  Exception {
        double charge = this.carServices.findCostForGivenDistance(new Date(),
                this.cityServices.findById(2), CarType.BASIC,40);
        assertEquals(1000, charge,0.1);

    }

    @Test
    public void findDriverForGivenCar() throws  Exception {
        Driver driver = this.carServices.findDriverForGivenCar(2);
        assertEquals("sudhir", driver.getName());

    }

    @Test
    public void findAllTripsForGivenCar() throws  Exception {
        List<Trip> trips = this.carServices.findAllTripsForGivenCar(2);
        assertEquals( 1, trips.size());
        assertEquals(2, trips.get(0).getId());

    }

}