package com.allstate.services;

import com.allstate.entities.Passanger;
import com.allstate.enums.Gender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(value = "/sql/seed.sql")
public class PassangerServicesTest {

    @Autowired
    private PassangerServices passangerServices;

    @Test
    public void shouldCreatePassanger() throws Exception{
        Passanger passanger = new Passanger();
        passanger.setName("Anusha");
        passanger.setAge(20);
        passanger.setGender(Gender.FEMALE);
        passanger.setBalance(2000);

        Passanger result = this.passangerServices.create(passanger);

        assertEquals(4, result.getId());
        assertEquals(2000, result.getBalance(),0.1);
    }

    @Test
    public void shouldFindPassangerById() throws Exception{
        Passanger result = this.passangerServices.findById(2);
        assertEquals("Abhi", result.getName());
        assertEquals(1000, result.getBalance(),0.1);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void shouldFindPassangerByIDAndThrowExceptionInCaseUsed() throws Exception {
        this.passangerServices.deleteById(2);
    }

    @Test
    public void shouldDeletePassangerByIDIfExists() throws Exception {
        this.passangerServices.deleteById(3);
        Passanger result = this.passangerServices.findById(3);
        assertNull(result);

    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void shouldDeletePassangerByIDIfDoesNotExists() throws Exception {
        this.passangerServices.deleteById(20);

    }


//
//    @Test
//    public void findAllPassangerTakenTripInACity() throws  Exception {
//        List<Passanger> listPassangers = this.tripServices.findAllPassangerTakenTripInACity(2);
//        assertEquals(1, listPassangers.size());
//        assertEquals("Abhi", listPassangers.get(0).getName());
//
//    }
//
//    @Test
//    public void findAllPassangerTakenTripInACity() throws  Exception {
//        List<Passanger> listPassangers = this.tripServices.findAllPassangerTakenTripInACity(2);
//        assertEquals(1, listPassangers.size());
//        assertEquals("Abhi", listPassangers.get(0).getName());
//
//    }

}