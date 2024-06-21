package com.tours.tourisme.service;

import com.tours.tourisme.model.BoundedPageSize;
import com.tours.tourisme.model.PageFromOne;
import com.tours.tourisme.model.exception.ResourceAlreadyExistsException;
import com.tours.tourisme.repository.TourRestaurantRepository;
import com.tours.tourisme.repository.entity.Restaurant;
import com.tours.tourisme.repository.entity.Tour;
import com.tours.tourisme.repository.entity.TourRestaurant;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TourRestaurantService {
    private final TourRestaurantRepository repository;
    private final TourService tourService;
    private final RestaurantService restaurantService;

    public List<Restaurant> getAllRestaurant(PageFromOne page, BoundedPageSize pageSize, Long tid){
        Pageable pageable = PageRequest.of(page.getValue() - 1, pageSize.getValue());
        return repository.findAllRestaurantByTourId(pageable, tid);
    }

    public TourRestaurant saveTourRestaurant(Long tid , Long rid){  //tid : tour id and rid : restaurant id
        Tour tour = tourService.getTourById(tid);
        Restaurant restaurant = restaurantService.getRestaurantById(rid);
        TourRestaurant existingTourRestaurant = repository.findByTourIdAndRestaurantId(tid, rid);

        if(existingTourRestaurant != null){
            throw new ResourceAlreadyExistsException("Restaurant with id :" + rid + " is already added to this tour");
        }

        TourRestaurant tourRestaurant = TourRestaurant.builder()
                .tour(tour)
                .restaurant(restaurant)
                .build();
        return repository.save(tourRestaurant);
    }

    public TourRestaurant deleteTourRestaurant(Long tid , Long rid){
        TourRestaurant tourRestaurantToDelete = repository.findByTourIdAndRestaurantId(tid, rid);
        repository.delete(tourRestaurantToDelete);
        return tourRestaurantToDelete;
    }
}
