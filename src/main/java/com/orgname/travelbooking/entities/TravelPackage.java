package com.orgname.travelbooking.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;


@Entity
@Table(name = "travel_packages")
@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class TravelPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer duration;
    private BigDecimal price;

    @ManyToMany
    @JoinTable(name = "travel_packages_destinations",
            joinColumns = @JoinColumn(name = "travel_package_id"),
            inverseJoinColumns = @JoinColumn(name = "destination_id")
    )
    private Set<Destination> destinations;

}



// Key = travelpackages/name-id;
















//    @ManyToMany
//    @JoinTable(name = "travel_packages_destinations",
//            joinColumns = @JoinColumn(name = "travel_package_id"),
//            inverseJoinColumns = @JoinColumn(name = "destination_id")
//    )
//    private Set<Destination> destinations;



//    @OneToMany(cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "travel_package_id")
//    private List<Passenger> passengers;