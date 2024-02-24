package com.orgname.travelbooking.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "passengers")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
}
















//    @ManyToMany
//    @JoinTable(name = "activity_passengers",
//            joinColumns = @JoinColumn(name = "passenger_id"),
//            inverseJoinColumns = @JoinColumn(name = "activity_id")
//    )
//    private List<Activity> activities;


