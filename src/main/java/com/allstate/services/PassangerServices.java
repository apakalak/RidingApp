package com.allstate.services;

import com.allstate.entities.Passanger;
import com.allstate.repositories.IPassangerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassangerServices {

    @Autowired
    private IPassangerRepository passangerRepo;

    public Passanger create(Passanger passanger){
        return this.passangerRepo.save(passanger);
    }

    public Passanger findById(int id) { return  this.passangerRepo.findOne(id); }

    public void deleteById(int id) {this.passangerRepo.delete(id); }
}
