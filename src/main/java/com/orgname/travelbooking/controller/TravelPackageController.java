package com.orgname.travelbooking.controller;

import com.orgname.travelbooking.dto.ResponseDto;
import com.orgname.travelbooking.dto.TravelPackageRequest;
import com.orgname.travelbooking.entities.TravelPackage;
import com.orgname.travelbooking.service.TravelPackageService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/package")
@RequiredArgsConstructor
public class TravelPackageController {

    private final TravelPackageService travelPackageService;


    @PostMapping(path = "/create")
    public ResponseEntity<ResponseDto> createTravelPackage(@RequestBody final TravelPackageRequest travelPackageRequest) {
        travelPackageService.createTravelPackage(travelPackageRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(String.valueOf(HttpStatus.CREATED), "Travel Package Created Successfully"));
    }


    @GetMapping(path = "/fetchall")
    public ResponseEntity<List<TravelPackage>> fetchAllTravelPackages() {
        List<TravelPackage> travelPackages = travelPackageService.fetchAllTravelPackages();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(travelPackages);
    }
}
