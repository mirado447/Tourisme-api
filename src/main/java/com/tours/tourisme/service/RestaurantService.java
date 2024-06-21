package com.tours.tourisme.service;

import com.tours.tourisme.model.BoundedPageSize;
import com.tours.tourisme.model.PageFromOne;
import com.tours.tourisme.model.exception.NotFoundException;
import com.tours.tourisme.repository.RestaurantRepository;
import com.tours.tourisme.repository.entity.Restaurant;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RestaurantService {
    private final RestaurantRepository repository;
    private final LocationService locationService;

    public List<Restaurant> getAllRestaurant(PageFromOne page, BoundedPageSize pageSize){
        Pageable pageable = PageRequest.of(page.getValue() - 1, pageSize.getValue());
        return repository.findAllRestaurant(pageable);
    }

    public Restaurant getRestaurantById(Long rid){
        return repository
                .findById(rid)
                .orElseThrow(() -> new NotFoundException("Restaurant with id " + rid + " not found"));
    }

    public Restaurant saveRestaurant(Restaurant restaurant){
        restaurant.setLocation(locationService.getLocationById(restaurant.getLocation().getId()));
        return repository.save(restaurant);
    }

    public Restaurant crupdateRestaurant(Long rid, Restaurant restaurant){
        Optional<Restaurant> optionalRestaurant = repository.findById(rid);
        if(optionalRestaurant.isPresent()){
            Restaurant restaurantFromDomain = optionalRestaurant.get();
            restaurant.setId(restaurantFromDomain.getId());
            restaurant.setLocation(locationService.getLocationById(restaurant.getLocation().getId()));
            return repository.save(restaurant);
        }else {
            return saveRestaurant(restaurant);
        }
    }
}
