package com.allstate.repositories;


import com.allstate.entities.Driver;
import org.springframework.data.repository.CrudRepository;

public interface IDriverRepository extends CrudRepository<Driver , Integer> {
}
