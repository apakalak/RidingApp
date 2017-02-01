package com.allstate.repositories;


import com.allstate.entities.Driver;
import com.allstate.entities.Trip;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IDriverRepository extends CrudRepository<Driver , Integer> {
    String queryToFindTripsWithDriver = "select t from Trip t join t.car c  join c.driver d where d.id = :id";
    @Query(queryToFindTripsWithDriver)
    public List<Trip> findAllTripsForGivenDriver(@Param("id") int id);
}
