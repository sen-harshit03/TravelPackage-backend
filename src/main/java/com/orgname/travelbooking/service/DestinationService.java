package com.orgname.travelbooking.service;

import com.orgname.travelbooking.dto.DestinationRequest;
import com.orgname.travelbooking.entities.Destination;

import java.util.List;

public interface DestinationService {
    void createDestination(DestinationRequest destinationRequest);

    List<String> getAllDestinations();

    List<Destination> getDestinations();
}
