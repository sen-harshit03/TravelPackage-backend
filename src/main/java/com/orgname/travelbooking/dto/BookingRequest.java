package com.orgname.travelbooking.dto;

import com.orgname.travelbooking.entities.Passenger;
import java.util.Set;

public record BookingRequest(Long travelPackageId, Set<Passenger> passengers) {
}
