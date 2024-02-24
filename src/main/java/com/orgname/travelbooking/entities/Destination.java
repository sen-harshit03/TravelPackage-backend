package com.orgname.travelbooking.entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;


@Entity
@Table(name = "destinations")
@Getter @Setter @AllArgsConstructor @Builder @NoArgsConstructor
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "destination_id")
    private Set<Activity> activities;
}




























//@ManyToMany(mappedBy = "destinations")
//    private Set<TravelPackage> travelPackageSet;
