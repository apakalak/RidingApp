package com.allstate.entities;


import com.allstate.enums.Gender;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "passangers")
@Data
public class Passanger {

    @Id
    @GeneratedValue
    private int id;

    @Version
    private int version;

    @NotNull
    private String name;

    @NotNull
    private int age;

    @Column(columnDefinition = "Enum('MALE','FEMALE')")
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @NotNull
    private double balance;

    @CreationTimestamp
    private Date created;

    @UpdateTimestamp
    private Date modified;

    @Override
    public String toString(){
        return this.name;
    }

//    @OneToMany(mappedBy = "passanger")
//    @JsonIgnore
//    private List<Trip> trips;

}
