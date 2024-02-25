package com.orgname.travelbooking.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orgname.travelbooking.dto.ResponseDto;
import com.orgname.travelbooking.dto.TravelPackageRequest;
import com.orgname.travelbooking.entities.TravelPackage;
import com.orgname.travelbooking.service.TravelPackageService;
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
@CrossOrigin("*")
public class TravelPackageController {

    private final TravelPackageService travelPackageService;


    @PostMapping(path = "/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ResponseDto> createTravelPackage(@RequestParam("files") MultipartFile[] files, @RequestParam String travelPackageRequestJson) {
        System.out.println(files.length);

        ObjectMapper objectMapper = new ObjectMapper();
        TravelPackageRequest travelPackageRequest;
        try {
            travelPackageRequest = objectMapper.readValue(travelPackageRequestJson, TravelPackageRequest.class);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
//        TravelPackageRequest request = new TravelPackageRequest("Fuddi", 12, Set.of("Fuddi"), BigDecimal.valueOf(450));
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
