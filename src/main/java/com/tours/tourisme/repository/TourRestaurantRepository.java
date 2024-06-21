package com.tours.tourisme.repository;

import com.tours.tourisme.repository.entity.Restaurant;
import com.tours.tourisme.repository.entity.TourRestaurant;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRestaurantRepository extends JpaRepository<TourRestaurant, Long> {
    @Query("select  tr from TourRestaurant tr where tr.tour.id = :tid and  tr.restaurant.id = :rid")
    TourRestaurant findByTourIdAndRestaurantId(Long tid, Long rid);

    @Query("select tr.restaurant from TourRestaurant tr where tr.tour.id = :tid")
    List<Restaurant> findAllRestaurantByTourId(Pageable pageable, Long tid);
}
