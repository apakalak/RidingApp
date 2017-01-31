package com.allstate.repositories;

import com.allstate.entities.Car;
import org.springframework.data.repository.CrudRepository;


public interface ICarRepository extends CrudRepository<Car, Integer> {
}
