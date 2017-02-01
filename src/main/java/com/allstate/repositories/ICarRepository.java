package com.allstate.repositories;

import com.allstate.entities.Car;
import com.allstate.entities.Trip;
import com.allstate.enums.CarType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ICarRepository extends CrudRepository<Car, Integer> {


    String queryToFindFirstCarAvailable = "SELECT c FROM Car c JOIN c.driver d JOIN d.city ci where  ci.name = :city AND c.type = :car_type AND d.no_of_voilation < 4 AND c.isAvailable = TRUE ";
    @Query(queryToFindFirstCarAvailable)
    public List<Car> findFirstOfAvailableCars(@Param("city") String city, @Param("car_type") CarType car_type);


    String queryToFindTripsGoneInCar = "select t from Trip t join t.car c where c.id = :id";
    @Query(queryToFindTripsGoneInCar)
    public List<Trip> findAllTripsForGivenCar(@Param("id") int id);
}
