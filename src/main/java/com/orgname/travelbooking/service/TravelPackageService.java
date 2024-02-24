package com.orgname.travelbooking.service;

import com.orgname.travelbooking.dto.DestinationRequest;
import com.orgname.travelbooking.dto.TravelPackageRequest;
import com.orgname.travelbooking.entities.TravelPackage;

import java.util.List;

public interface TravelPackageService {

    void createTravelPackage(final TravelPackageRequest travelPackageRequest);

    List<TravelPackage> fetchAllTravelPackages();
}
