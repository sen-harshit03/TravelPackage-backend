package com.orgname.travelbooking.service.impl;

import com.orgname.travelbooking.dto.BookingRequest;
import com.orgname.travelbooking.entities.Booking;
import com.orgname.travelbooking.entities.TravelPackage;
import com.orgname.travelbooking.exceptions.NotEnoughCapacityException;
import com.orgname.travelbooking.exceptions.ResourceNotFoundException;
import com.orgname.travelbooking.repository.BookingRepository;
import com.orgname.travelbooking.repository.TravelPackageRepository;
import com.orgname.travelbooking.service.BookingService;
import com.orgname.travelbooking.utils.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookingServiceImpl implements BookingService {

    private final TravelPackageRepository travelPackageRepository;
    private final BookingRepository bookingRepository;
    private final Mapper mapper;

    @Override
    public void createBooking(BookingRequest bookingRequest) {

        TravelPackage travelPackage = travelPackageRepository.findById(bookingRequest.travelPackageId()).orElseThrow(
                () -> new ResourceNotFoundException("This travel package might have been expired. Sorry for the inconvenience")
        );

        int passengerCount = bookingRequest.passengers().size();
//        if(passengerCount > travelPackage.getAvailableCapacity()) {
//            throw new NotEnoughCapacityException(
//                    String.format("Cannot confirm booking for %s passengers, only %s slots available in %s travel package",
//                            passengerCount, travelPackage.getAvailableCapacity(), travelPackage.getName()));
//        }

        Booking booking = mapper.mapToBooking(bookingRequest);
//        travelPackage.setAvailableCapacity(travelPackage.getAvailableCapacity() - passengerCount);

        bookingRepository.save(booking);
    }
}
