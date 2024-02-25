package com.orgname.travelbooking.service;

import com.orgname.travelbooking.dto.DestinationRequest;
import com.orgname.travelbooking.dto.TravelPackageRequest;
import com.orgname.travelbooking.entities.TravelPackage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TravelPackageService {

    void createTravelPackage(final MultipartFile[] files, final String travelPackageRequestJson);

    List<TravelPackage> fetchAllTravelPackages();
}
