package com.tours.tourisme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItineraryRepository extends JpaRepository<com.tours.tourisme.entity.Itinerary, Long> {
}
