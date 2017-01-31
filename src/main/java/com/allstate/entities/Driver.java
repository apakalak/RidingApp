package com.allstate.entities;

import com.allstate.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="drivers")
@Data
public class Driver {

    @Id
    @GeneratedValue
    private  int id;

    @Version
    private int version;

    @NotNull
    private String name;

    @NotNull
    private int age;

    @Column(columnDefinition = "Enum('MALE','FEMALE')")
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    private  int no_of_voilation;

    @CreationTimestamp
    private Date created;

    @UpdateTimestamp
    private Date modified;

    @OneToMany(mappedBy = "driver")
    @JsonIgnore
    private List<Car> cars;

    @ManyToOne
    @JoinColumn(name="city_id")
    @JsonIgnore
    private City city;

    @Override
    public String toString(){
        return this.name;
    }


}
