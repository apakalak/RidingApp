package com.allstate.services;

import com.allstate.entities.City;
import com.allstate.entities.Driver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(value = "/sql/seed.sql")
public class CityServicesTest {

    @Autowired
    private CityServices cityServices;

    @Test
    public void createCity() throws Exception{
        City city = new City();
        city.setName("Bengaluru");
        city.setState("Karnataka");
        city.setCountry("India");
        city.setDay_charge(20);
        city.setNight_charge(30);
        city.setLux_tax(5);

        City result= this.cityServices.create(city);

        assertEquals(5,result.getId());
        assertEquals("Bengaluru",result.getName());
        assertEquals("Karnataka",result.getState());
        assertEquals("India",result.getCountry());
        assertEquals(20,result.getDay_charge());
        assertEquals(30,result.getNight_charge());
        assertEquals(5,result.getLux_tax(),0.1);
        assertEquals(0,result.getVersion());
    }


    @Test
    public void findById() throws Exception{
        City result = this.cityServices.findById(3);
        assertEquals(3,result.getId());
        assertEquals("mysuru",result.getName());
        assertEquals("karnataka",result.getState());
        assertEquals("india",result.getCountry());
        assertEquals(15,result.getDay_charge());
        assertEquals(20,result.getNight_charge());
        assertEquals(5,result.getLux_tax(),0.1);
        assertEquals(0,result.getVersion());

    }

    @Test(expected = DataIntegrityViolationException.class)
    public void shouldFindCityByIDAndThrowExceptionInCaseUsed() throws Exception {
        this.cityServices.deleteById(2);
    }

    @Test
    public void shouldDeleteCityByIDIfExists() throws Exception {
        this.cityServices.deleteById(4);
        City result = this.cityServices.findById(4);
        assertNull(result);

    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void shouldDeleteCityByIDIfDoesNotExists() throws Exception {
        this.cityServices.deleteById(20);
    }

    @Test
    public void fetchAllDriversInACity() throws Exception {
        List<Driver> drivers = this.cityServices.findAllDriversInACity("bengluru");
        assertEquals(1, drivers.size());
        assertEquals("magdhir", drivers.get(0).getName());

    }
}