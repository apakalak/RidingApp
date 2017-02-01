package com.allstate.repositories;

import com.allstate.entities.City;
import com.allstate.entities.Driver;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ICityRepository extends CrudRepository<City , Integer>{
    String query = "select d from Driver d join d.city ci where ci.name = :cityName" ;
    @Query( query)
    List<Driver> findAllDriversInACity(@Param("cityName") String cityName);
}
