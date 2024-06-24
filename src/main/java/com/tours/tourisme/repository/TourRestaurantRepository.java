package com.tours.tourisme.repository;

import com.tours.tourisme.repository.entity.Restaurant;
import com.tours.tourisme.repository.entity.TourRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TourRestaurantRepository extends JpaRepository<TourRestaurant, Long> {
    @Query("select  tr from TourRestaurant tr where tr.tour.id = :tid and  tr.restaurant.id = :rid")
    Optional<TourRestaurant> findByTourIdAndRestaurantId(Long tid, Long rid);

    @Query("select tr.restaurant from TourRestaurant tr where tr.tour.id = :tid")
    List<Restaurant> findAllRestaurantByTourId(Long tid);
}
