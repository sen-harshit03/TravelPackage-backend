package com.orgname.travelbooking.utils;

import com.orgname.travelbooking.dto.BookingRequest;
import com.orgname.travelbooking.dto.DestinationRequest;
import com.orgname.travelbooking.dto.TravelPackageRequest;
import com.orgname.travelbooking.entities.Booking;
import com.orgname.travelbooking.entities.Destination;
import com.orgname.travelbooking.entities.TravelPackage;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;


@Component
public class Mapper {

    public Destination mapToDestination(final DestinationRequest destinationRequest) {
        return Destination.builder()
                .name(destinationRequest.name())
                .activities(destinationRequest.activities())
                .build();
    }

    public TravelPackage mapToTravelPackage(final TravelPackageRequest travelPackageRequest,
                                            final Set<Destination> destinationSet
    ) {

        final TravelPackage travelPackage = TravelPackage.builder()
                .name(travelPackageRequest.name())
                .duration(travelPackageRequest.duration())
                .price(travelPackageRequest.price())
                .destinations(destinationSet)
                .build();
        return travelPackage;
    }

    public Booking mapToBooking(final BookingRequest bookingRequest) {
        return Booking.builder()
                .travelPackageId(bookingRequest.travelPackageId())
                .bookingTime(LocalDateTime.now())
                .passengers(bookingRequest.passengers())
                .build();
    }
}
