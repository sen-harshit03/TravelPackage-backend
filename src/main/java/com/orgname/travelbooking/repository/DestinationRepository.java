package com.orgname.travelbooking.repository;

import com.orgname.travelbooking.entities.Destination;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface DestinationRepository extends JpaRepository<Destination, Long> {

    @Query("""
            SELECT d FROM Destination d WHERE d.name = :name
            """)
    Optional<Destination> findByName(String name);


    @Query("SELECT d.name FROM Destination d")
    List<String> findAllByName();


    @Query("""
            SELECT d FROM Destination d
            LEFT JOIN FETCH d.activities a
            WHERE d.name IN :names
            """)
    List<Destination> findByNameIn(Set<String> names);


//    @EntityGraph(value = "Destination.fetchActivitiesEagerly", type = EntityGraph.EntityGraphType.FETCH)
    @Query("""
            SELECT d FROM Destination d
            LEFT JOIN FETCH d.activities a
            """)
    List<Destination> findAll();
}

//    LEFT JOIN FETCH a.passengers
//LEFT JOIN FETCH a.passengers