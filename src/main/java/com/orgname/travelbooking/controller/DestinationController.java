package com.orgname.travelbooking.controller;

import com.orgname.travelbooking.dto.DestinationRequest;
import com.orgname.travelbooking.dto.ResponseDto;
import com.orgname.travelbooking.entities.Activity;
import com.orgname.travelbooking.entities.Destination;
import com.orgname.travelbooking.service.DestinationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/destination")
@AllArgsConstructor
@CrossOrigin("*")
public class DestinationController {

    private final DestinationService destinationService;

    @PostMapping(path = "/create")
    public ResponseEntity<ResponseDto> createDestination(@RequestBody final DestinationRequest destinationRequest) {
        destinationService.createDestination(destinationRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(String.valueOf(HttpStatus.CREATED), "Destination Created Successfully!"));
    }

    @GetMapping(path = "/fetchnames")
    public ResponseEntity<List<String>> getAllDestinations() {
        List<String> destinations = destinationService.getAllDestinations();
        return ResponseEntity.status(HttpStatus.OK)
                .body(destinations);
    }

    @GetMapping(path = "/fetch")
    public ResponseEntity<List<Destination>> getDestinations() {
        List<Destination> destinations = destinationService.getDestinations();
        return ResponseEntity.status(HttpStatus.OK)
                .body(destinations);
    }
}
