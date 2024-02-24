package com.orgname.travelbooking.service.impl;

import com.orgname.travelbooking.dto.DestinationRequest;
import com.orgname.travelbooking.entities.Destination;
import com.orgname.travelbooking.exceptions.ResourceAlreadyExistsException;
import com.orgname.travelbooking.repository.DestinationRepository;
import com.orgname.travelbooking.service.DestinationService;
import com.orgname.travelbooking.utils.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DestinationServiceImpl implements DestinationService {
    private final DestinationRepository destinationRepository;

    private final Mapper mapper;


    @Override
    public void createDestination(final DestinationRequest destinationRequest) {
        final Optional<Destination> optionalDestination = destinationRepository.findByName(destinationRequest.name());
        if(optionalDestination.isPresent()) {
            throw new ResourceAlreadyExistsException(
                    String.format("Destination already exists for %s ", destinationRequest.name())
            );
        }

        final Destination destination = mapper.mapToDestination(destinationRequest);
        destinationRepository.save(destination);
    }

    @Override
    public List<String> getAllDestinations() {
        List<String> destinations = destinationRepository.findAllByName();
        return destinations;
    }

    @Override
    public List<Destination> getDestinations() {
        List<Destination> destinations = destinationRepository.findAll();
        return destinations;
    }
}
