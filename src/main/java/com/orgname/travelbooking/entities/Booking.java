package com.orgname.travelbooking.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Long travelPackageId;
    private LocalDateTime bookingTime;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "booking_id")
    private Set<Passenger> passengers;
}
