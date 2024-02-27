package com.orgname.travelbooking.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orgname.travelbooking.dto.TravelPackageRequest;
import com.orgname.travelbooking.dto.TravelPackageResponse;
import com.orgname.travelbooking.entities.Destination;
import com.orgname.travelbooking.entities.TravelPackage;
import com.orgname.travelbooking.exceptions.ResourceAlreadyExistsException;
import com.orgname.travelbooking.repository.DestinationRepository;
import com.orgname.travelbooking.repository.TravelPackageRepository;
import com.orgname.travelbooking.service.TravelPackageService;
import com.orgname.travelbooking.utils.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Object;


import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TravelPackageServiceImpl implements TravelPackageService {

    private final TravelPackageRepository travelPackageRepository;
    private final DestinationRepository destinationRepository;
    private final Mapper mapper;
    private final ObjectMapper objectMapper;
    private final S3Service s3Service;



    @Override
    public void createTravelPackage(final MultipartFile[] files, final String travelPackageRequestJson) {
        System.out.println(files.length);
        final TravelPackageRequest travelPackageRequest = convertStringToObject(travelPackageRequestJson, TravelPackageRequest.class);

        final Optional<TravelPackage> optionalTravelPackage =
                travelPackageRepository.findByName(travelPackageRequest.name());
        if(optionalTravelPackage.isPresent()) {
            throw new ResourceAlreadyExistsException(
                    String.format("Travel Package already exists with name %s ", travelPackageRequest.name())
            );
        }

        final Set<Destination> destinationSet = getDestinationsByName(travelPackageRequest.destinationNames());
        destinationSet.stream().forEach(d -> System.out.println(d.getName()));

        final TravelPackage travelPackage = mapper.mapToTravelPackage(travelPackageRequest, destinationSet);
        final TravelPackage savedTravelPackage = travelPackageRepository.save(travelPackage);
        try {
            s3Service.uploadImage(savedTravelPackage.getId(), savedTravelPackage.getName(), files);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public List<TravelPackageResponse> fetchAllTravelPackages() {
        final List<TravelPackage> travelPackages = travelPackageRepository.findAll();
        final List<TravelPackageResponse> travelPackageResponses = getTravelPackagesResponses(travelPackages);
        return travelPackageResponses;
    }



    private Set<Destination> getDestinationsByName(final Set<String> destinationsNames) {
        final List<Destination> destinationList = destinationRepository.findByNameIn(destinationsNames);
        final Set<Destination> destinationSet = new HashSet<>(destinationList);
        return destinationSet;
    }


    private TravelPackageRequest convertStringToObject(final String stringValue, final Class<TravelPackageRequest> classType) {
        try {
            return objectMapper.readValue(stringValue, classType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    private List<TravelPackageResponse> getTravelPackagesResponses(final List<TravelPackage> travelPackages) {
        final List<TravelPackageResponse> travelPackageResponses = new ArrayList<>();
        travelPackages
                .forEach(travelPackage -> {
                    final List<S3Object> s3Objects = s3Service.getObjectList(travelPackage.getId(), travelPackage.getName());
                    final List<byte[]> images = new ArrayList<>();
                    s3Objects.forEach(
                            s3Object -> {
                                final String key = s3Object.key();
                                final byte[] imageBytes = s3Service.getObject(key);
                                images.add(imageBytes);
                            }
                    );
                    final TravelPackageResponse travelPackageResponse = mapper.mapToTravelPackageResponse(travelPackage, images);
                    travelPackageResponses.add(travelPackageResponse);
                });
        return travelPackageResponses;
    }


}





















//    private void updateDestinationsWithTravelPackage(final Set<Destination> destinationSet,
//                                                      final TravelPackage travelPackage) {
//        destinationSet
//                .forEach(destination -> destination.getTravelPackageSet().add(travelPackage));
//        System.out.println(destinationSet);
//    }
