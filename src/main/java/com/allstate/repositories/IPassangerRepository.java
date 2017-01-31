package com.allstate.repositories;

import com.allstate.entities.Passanger;
import org.springframework.data.repository.CrudRepository;

public interface IPassangerRepository extends CrudRepository<Passanger, Integer> {
}
