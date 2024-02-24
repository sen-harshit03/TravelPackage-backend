package com.orgname.travelbooking.repository;

import com.orgname.travelbooking.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, String> {
}
