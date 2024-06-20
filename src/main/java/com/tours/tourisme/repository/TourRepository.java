package com.tours.tourisme.repository;

import com.tours.tourisme.repository.entity.Tour;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {
    @Query("SELECT t from Tour t")
    List<Tour> findAllTour(Pageable pageable);
}