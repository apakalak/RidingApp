package com.allstate.services;


import com.allstate.entities.Trip;
import com.allstate.repositories.ITripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TripServices {
    @Autowired
    private ITripRepository tripRepository;

    public Trip create(Trip trip) {

       return this.tripRepository.save(trip);
    }
}
