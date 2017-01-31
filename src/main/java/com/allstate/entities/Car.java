package com.allstate.entities;


import com.allstate.enums.CarType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cars")
@Data
public class Car {

    @Id
    @GeneratedValue
    private int id;

    @Version
    private int version;

    @NotNull
    private String make;

    @NotNull
    private String model;

    @NotNull
    private int year;

    @Column(columnDefinition = "Enum('BASIC','LUX')")
    @Enumerated(value = EnumType.STRING)
    private CarType type;

    @CreationTimestamp
    private Date created;

    @UpdateTimestamp
    private Date modified;

    @ManyToOne
    @JoinColumn(name="driver_id")
    @JsonIgnore
    private Driver driver;

    @Override
    public String toString(){
        return this.make;
    }

//    @OneToMany(mappedBy = "car")
//    @JsonIgnore
//    private List<Trip> trips;


}
