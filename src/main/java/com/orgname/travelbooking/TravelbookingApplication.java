package com.orgname.travelbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TravelbookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelbookingApplication.class, args);
	}

}


/**
 *  API'S for customer:
 *  1. getAllTravelPackage - to load data to dashboard
 *
 *
 *  API's for ADMIN:
 *  1. CreateTravelPackage:
 *  2. CreateDestination and TravelPackage
 *
 *
 *  Himachal Travels passengers: travelpackge -> destination -> activities -> Customer
 *  bookpackage:
 *   Booking:
 *    - travelpackage
 *    - List<Activity>
 *   choose activities:
 *   activity1.getCustomer.add(p1)
 *
 */
