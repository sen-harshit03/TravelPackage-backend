package com.orgname.travelbooking.service.impl;

import com.orgname.travelbooking.dto.TravelPackageRequest;
import com.orgname.travelbooking.entities.Destination;
import com.orgname.travelbooking.entities.TravelPackage;
import com.orgname.travelbooking.exceptions.ResourceAlreadyExistsException;
import com.orgname.travelbooking.repository.DestinationRepository;
import com.orgname.travelbooking.repository.TravelPackageRepository;
import com.orgname.travelbooking.service.TravelPackageService;
import com.orgname.travelbooking.utils.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TravelPackageServiceImpl implements TravelPackageService {

    private final TravelPackageRepository travelPackageRepository;
    private final DestinationRepository destinationRepository;
    private final Mapper mapper;


    @Override
    public void createTravelPackage(final TravelPackageRequest travelPackageRequest) {
        final Optional<TravelPackage> optionalTravelPackage =
                travelPackageRepository.findByName(travelPackageRequest.name());
        if(optionalTravelPackage.isPresent()) {
            throw new ResourceAlreadyExistsException(
                    String.format("Travel Package already exists with name %s ", travelPackageRequest.name())
            );
        }

        final Set<Destination> destinationSet = getDestinationsByName(travelPackageRequest.destinationsNames());
        destinationSet.stream().forEach(d -> System.out.println(d.getName()));
        // Handled at frontend
//        if(destinationSet.size() != travelPackageRequest.destinationsNames().size()) throw Exception("Dsd");

        final TravelPackage travelPackage = mapper.mapToTravelPackage(travelPackageRequest, destinationSet);
        final TravelPackage savedTravelPackage = travelPackageRepository.save(travelPackage);
//        updateDestinationsWithTravelPackage(destinationSet, savedTravelPackage);
    }



    @Override
    public List<TravelPackage> fetchAllTravelPackages() {
        List<TravelPackage> travelPackages = travelPackageRepository.findAll();
        return travelPackages;
    }


    private Set<Destination> getDestinationsByName(final Set<String> destinationsNames) {
        List<Destination> destinationList = destinationRepository.findByNameIn(destinationsNames);
        Set<Destination> destinationSet = new HashSet<>(destinationList);
        return destinationSet;
    }
}





















//    private void updateDestinationsWithTravelPackage(final Set<Destination> destinationSet,
//                                                      final TravelPackage travelPackage) {
//        destinationSet
//                .forEach(destination -> destination.getTravelPackageSet().add(travelPackage));
//        System.out.println(destinationSet);
//    }
