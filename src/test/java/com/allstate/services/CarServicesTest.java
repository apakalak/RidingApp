package com.allstate.services;

import com.allstate.entities.Car;
import com.allstate.enums.CarType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.Iterator;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(value = "/sql/seed.sql")
public class CarServicesTest {

    @Autowired
    private CarServices carServices;

    @Autowired
    private DriverServices driverServices;

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

}