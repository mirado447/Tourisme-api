package com.tours.tourisme.repository;

import com.tours.tourisme.repository.entity.Itinerary;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {
    @Query("SELECT i from Itinerary i")
    List<Itinerary> findAllItinerary(Pageable pageable) ;

    Optional<Itinerary> findById(Long id);
}
