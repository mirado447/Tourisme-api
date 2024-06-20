package com.tours.tourisme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<com.tours.tourisme.entity.Location, Long> {
}
