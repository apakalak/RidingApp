package com.allstate.repositories;

import com.allstate.entities.City;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by localadmin on 31/01/17.
 */
public interface ICityRepository extends CrudRepository<City , Integer>{
}
