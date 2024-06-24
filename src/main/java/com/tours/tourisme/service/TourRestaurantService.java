package com.tours.tourisme.service;

import com.tours.tourisme.model.exception.NotFoundException;
import com.tours.tourisme.model.exception.ResourceAlreadyExistsException;
import com.tours.tourisme.repository.TourRestaurantRepository;
import com.tours.tourisme.repository.entity.Restaurant;
import com.tours.tourisme.repository.entity.Tour;
import com.tours.tourisme.repository.entity.TourRestaurant;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TourRestaurantService {
    private final TourRestaurantRepository repository;
    private final TourService tourService;
    private final RestaurantService restaurantService;

    public List<Restaurant> getAllRestaurant(Long tid){
        return repository.findAllRestaurantByTourId(tid);
    }

    public TourRestaurant saveTourRestaurant(Long tid , Long rid){  //tid : tour id and rid : restaurant id
        Tour tour = tourService.getTourById(tid);
        Restaurant restaurant = restaurantService.getRestaurantById(rid);
        Optional<TourRestaurant> existingTourRestaurant = repository.findByTourIdAndRestaurantId(tid, rid);

        if(existingTourRestaurant.isPresent()){
            throw new ResourceAlreadyExistsException("Restaurant with id " + rid + " is already added to this tour");
        }

        TourRestaurant tourRestaurant = TourRestaurant.builder()
                .tour(tour)
                .restaurant(restaurant)
                .build();
        return repository.save(tourRestaurant);
    }

    public TourRestaurant deleteTourRestaurant(Long tid , Long rid){
        Optional<TourRestaurant> tourRestaurantToDelete = repository.findByTourIdAndRestaurantId(tid,rid);

        if(tourRestaurantToDelete.isPresent()){
            repository.delete(tourRestaurantToDelete.get());
        }else throw new NotFoundException("Restaurant with id " + rid + " not found");

        return tourRestaurantToDelete.get();
    }
}