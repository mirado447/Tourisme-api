package com.tours.tourisme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<com.tours.tourisme.entity.Restaurant, Long> {
}
