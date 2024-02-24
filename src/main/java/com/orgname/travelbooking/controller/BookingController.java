package com.orgname.travelbooking.controller;


import com.orgname.travelbooking.dto.BookingRequest;
import com.orgname.travelbooking.dto.ResponseDto;
import com.orgname.travelbooking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/book")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping(path = "/create")
    public ResponseEntity<ResponseDto> createBooking(@RequestBody final BookingRequest bookingRequest) {
        bookingService.createBooking(bookingRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(String.valueOf(HttpStatus.CREATED), "Travel package booked Successfully!"));
    }

}
