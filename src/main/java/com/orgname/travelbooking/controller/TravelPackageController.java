package com.orgname.travelbooking.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orgname.travelbooking.dto.ResponseDto;
import com.orgname.travelbooking.dto.TravelPackageRequest;
import com.orgname.travelbooking.dto.TravelPackageResponse;
import com.orgname.travelbooking.entities.TravelPackage;
import com.orgname.travelbooking.service.TravelPackageService;
import com.orgname.travelbooking.utils.Serializer;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
//git push orgin master

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/package")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TravelPackageController {

    private final TravelPackageService travelPackageService;

    @PostMapping(path = "/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ResponseDto> createTravelPackage(@RequestParam("files") MultipartFile[] files, @RequestParam("travelPackageRequestJson") String travelPackageRequestJson) {

        travelPackageService.createTravelPackage(files, travelPackageRequestJson);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(String.valueOf(HttpStatus.CREATED), "Travel Package Created Successfully"));
    }


    @GetMapping(path = "/fetchall")
    public ResponseEntity<List<TravelPackageResponse>> fetchAllTravelPackages() {
        List<TravelPackageResponse> travelPackages = travelPackageService.fetchAllTravelPackages();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(travelPackages);
    }
}
