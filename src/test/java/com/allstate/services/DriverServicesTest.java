package com.allstate.services;

import com.allstate.entities.Car;
import com.allstate.entities.Driver;
import com.allstate.entities.Trip;
import com.allstate.enums.Gender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@SpringBootTest
@RunWith(SpringRunner.class)
@Sql(value = "/sql/seed.sql")
public class DriverServicesTest {

    @Autowired
    private  DriverServices driverServices;

    @Autowired
    private  CityServices cityServices;

    @Test
    public void shouldCreateDriver(){

        Driver driver = new Driver();
        driver.setName("krishna");
        driver.setAge(23);
        driver.setGender(Gender.MALE);

        driver.setCity(this.cityServices.findById(3));

        Driver result= this.driverServices.create(driver);

        assertEquals(5,result.getId());
        assertEquals("krishna",result.getName());
        assertEquals(23,result.getAge());
        assertEquals(3,result.getCity().getId());
        assertEquals(0,result.getVersion());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void shouldFindDriverByIDAndThrowExceptionInCaseUsed() throws Exception {
        this.driverServices.deleteById(2);
    }

    @Test
    public void shouldDeleteDriverByIDIfExists() throws Exception {
        this.driverServices.deleteById(4);
        Driver result = this.driverServices.findById(4);
        assertNull(result);

    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void shouldDeleteDriverByIDIfDoesNotExists() throws Exception {
        this.driverServices.deleteById(20);
    }

    @Test
    public void addVoilationToDriver() throws Exception {
        this.driverServices.addVoilation(2);
        Driver d = this.driverServices.findById(2);
        assertEquals(2,d.getNo_of_voilation());

    }

    @Test
    public void addMultipleVOilation() throws Exception {
        this.driverServices.addVoilation(3);
        this.driverServices.addVoilation(3);
        this.driverServices.addVoilation(3);
        this.driverServices.addVoilation(3);
        Driver d = this.driverServices.findById(3);
        assertEquals(7,d.getNo_of_voilation());
    }

    @Test
    @Transactional
    public void findAllTheCarsOwnByDriver() throws Exception {
        List<Car> cars = this.driverServices.findAllTheCarsOwnByDriver(2);
        assertEquals(3, cars.size());
        assertEquals("SX4", cars.get(0).getModel());
    }

    @Test
    public void findAllTripsForGivenDriver() throws  Exception {
        List<Trip> trips = this.driverServices.findAllTripsForGivenDriver(2);
        assertEquals( 1, trips.size());
        assertEquals(1, trips.get(0).getId());

    }

}