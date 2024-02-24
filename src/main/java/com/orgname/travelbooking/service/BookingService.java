package com.orgname.travelbooking.service;

import com.orgname.travelbooking.dto.BookingRequest;

public interface BookingService {

    void createBooking(final BookingRequest bookingRequest);

}