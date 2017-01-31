package com.allstate.repositories;

import com.allstate.entities.Trip;
import org.springframework.data.repository.CrudRepository;


public interface ITripRepository extends CrudRepository<Trip , Integer> {
}
