package com.orgname.travelbooking.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;


public record TravelPackageRequest(
        String name,
        Integer duration,
        Set<String> destinationNames,
        BigDecimal price
) {
}


/**  I as an ADMIN:
 *   invoke createPackage:
 *
 *   name: Manali Tour package
 *   days: 4
 *
 *   dest: manali, Kullu
 *
 *
 *   passegerCapa: 10
 *
 *
 *
 *
 */