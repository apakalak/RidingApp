package com.allstate.services;

import com.allstate.entities.Trip;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import sun.util.calendar.BaseCalendar;


import javax.transaction.Transactional;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(value = "/sql/seed.sql")
public class TripServicesTest {

    @Autowired
    private  TripServices tripServices;

    @Autowired
    private CarServices carServices;

    @Autowired
    private PassangerServices passangerServices;

    @Test
    @Transactional
    public void shouldCreateTrip() throws  Exception{

        Trip trip = new Trip();
        trip.setStartTime(new Date());
        trip.setStopTime(new Date());
        trip.setPassangerReview("");
        trip.setDriverReview("");
        trip.setCar(this.carServices.findById(2));
        trip.setPassanger(this.passangerServices.findById(1));

        Trip result =this.tripServices.create(trip);

        assertEquals(3, result.getId());
        assertEquals("zen", result.getCar().getModel());
        assertEquals("Rishi", result.getPassanger().getName());
        assertEquals(0, result.getVersion());
    }


}