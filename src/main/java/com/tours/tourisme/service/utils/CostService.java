package com.tours.tourisme.service.utils;

import com.tours.tourisme.repository.TourInterestPointRepository;
import com.tours.tourisme.repository.TourRestaurantRepository;
import com.tours.tourisme.repository.entity.InterestPoint;
import com.tours.tourisme.repository.entity.Restaurant;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class CostService {
    private final TourRestaurantRepository tourRestaurantRepository;
    private final TourInterestPointRepository tourInterestPointRepository;

    public BigDecimal totalCost(Long tourId) {
        BigDecimal basicCost = BigDecimal.valueOf(500);

        List<Restaurant> restaurants = tourRestaurantRepository.findAllRestaurantByTourId(tourId);
        BigDecimal restaurantsCost = restaurants.stream()
                .map(Restaurant::getCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        List<InterestPoint> interestPoints = tourInterestPointRepository.findAllInterestPointByTourId(tourId);
        BigDecimal interestPointsCost = interestPoints.stream()
                .map(InterestPoint::getCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return basicCost.add(restaurantsCost).add(interestPointsCost);
    }
}
