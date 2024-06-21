package com.tours.tourisme.repository;

import com.tours.tourisme.repository.entity.Location;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    @Query("SELECT l from Location l")
    List<Location> findAllLocation(Pageable pageable);
}
