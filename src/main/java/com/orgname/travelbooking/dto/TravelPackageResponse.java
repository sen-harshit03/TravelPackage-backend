package com.orgname.travelbooking.dto;

import com.orgname.travelbooking.entities.Destination;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public record TravelPackageResponse(
        Long id,
        String name,
        Integer duration,
        BigDecimal price,
        Set<Destination>destinations,
        List<byte[]> images
) {
}
