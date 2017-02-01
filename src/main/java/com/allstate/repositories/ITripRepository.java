package com.allstate.repositories;

import com.allstate.entities.Driver;
import com.allstate.entities.Passanger;
import com.allstate.entities.Trip;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ITripRepository extends CrudRepository<Trip , Integer> {

    String queryFindAllPassangerTakenTripInACity = "select p from Trip t join t.car c  join c.driver d join d.city ci join t.passanger p where ci.id = :id ";
    @Query(queryFindAllPassangerTakenTripInACity)
    public List<Passanger> findAllPassangerTakenTripInACity(@Param("id") int id);


    String queryFindAllDriversForAGivenPassanger = "select d from Trip t join t.car c  join c.driver d  join t.passanger p where p.id = :id ";
    @Query(queryFindAllDriversForAGivenPassanger)
    List<Driver> findAllDriversForAGivenPassanger(@Param("id") int id);


    String queryfindTotalExpenditureByAPassangerOnTrips = "select sum(t.totalCost) from Trip t  join t.passanger p where p.id = :id ";
    @Query(queryfindTotalExpenditureByAPassangerOnTrips)
    double findTotalExpenditureByAPassangerOnTrips(@Param("id") int id);

    @Query(queryfindTotalExpenditureByAPassangerOnTrips)
    double findTotalExpenditureByAPassangerOnTripsOnPastDay(@Param("id") int id);
}
