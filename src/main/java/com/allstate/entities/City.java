package com.allstate.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "cities")
@Data
public class City {
    @Id
    @GeneratedValue
    private int id;

    @Version
    private int version;

    @NotNull
    private String name;

    @NotNull
    private String state;

    @NotNull
    private String country;

    @NotNull
    private int day_charge;

    @NotNull
    private int night_charge;

    private double lux_tax;

    @CreationTimestamp
    private Date created;

    @UpdateTimestamp
    private Date modified;

    @Override
    public String toString(){
        return this.name;
    }

}
