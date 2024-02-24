package com.orgname.travelbooking.repository;

import com.orgname.travelbooking.entities.TravelPackage;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface TravelPackageRepository extends JpaRepository<TravelPackage, Long> {

    @Query("""
            SELECT tp FROM TravelPackage tp
            LEFT JOIN FETCH tp.destinations d
            LEFT JOIN FETCH d.activities a
            """)
    List<TravelPackage> findAll();

    @Query("""
            SELECT tp FROM TravelPackage tp WHERE tp.name = :name
            """)
    Optional<TravelPackage> findByName(String name);
}

//    LEFT JOIN FETCH a.passengers
