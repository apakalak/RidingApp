package com.allstate.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "trips")
@Data
public class Trip {

    @Id
    @GeneratedValue
    private int id;

    @Version
    private int version;

    @NotNull
    @ColumnDefault(value = "0")
    private int duration;

    @NotNull
    private Date startTime;

    @NotNull
    private Date stopTime;

    @NotNull
    @ColumnDefault(value = "0")
    private double tripDistance;

    @NotNull
    @ColumnDefault(value = "0")
    private double cost;

    @NotNull
    @ColumnDefault(value = "0")
    private double tipPercentage;

    @NotNull
    @ColumnDefault(value = "0")
    private double totalCost;

    @CreationTimestamp
    private Date created;

    @UpdateTimestamp
    private Date modified;

    @ManyToOne
    @JoinColumn(name="car_id")
    @JsonIgnore
    private Car car;

    @ManyToOne
    @JoinColumn(name="pass_id")
    @JsonIgnore
    private Passanger passanger;

    @ColumnDefault(value = "0")
    private int passangerPoint;

    private String passangerReview;

    @ColumnDefault(value = "0")
    private int driverPoint;

    private String driverReview;

    @Override
    public String toString(){
        return "Trip";
    }

}
