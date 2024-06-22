package com.tours.tourisme.repository;

import com.tours.tourisme.repository.entity.Location;
import com.tours.tourisme.repository.entity.Tour;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.math.BigDecimal;
import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {
    @Query("SELECT t from Tour t")
    List<Tour> findAllTour(Pageable pageable);

    @Query("SELECT t FROM Tour t " +
            "WHERE t.cost <= :maxBudget " +
            "AND t.duration <= :maxDuration " +
            "AND t.itinerary.start_location = :startLocation " +
            "AND t.itinerary.end_location = :endLocation")
    List<Tour> findToursByPreferences(
            @Param("maxBudget") BigDecimal maxBudget,
            @Param("maxDuration") int maxDuration,
            @Param("startLocation") Location startLocation,
            @Param("endLocation") Location endLocation);
}