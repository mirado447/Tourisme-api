package com.tours.tourisme.endpoint.controller;

import com.tours.tourisme.repository.entity.Restaurant;
import com.tours.tourisme.repository.entity.TourRestaurant;
import com.tours.tourisme.service.TourRestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class TourRestaurantController {
    private final TourRestaurantService tourRestaurantService;

    @GetMapping("/tours/{tid}/restaurants")
    public List<Restaurant> getAllRestaurant(@PathVariable Long tid){
        return tourRestaurantService.getAllRestaurant(tid);
    }

    @PostMapping("/tours/{tid}/restaurants/{rid}")
    public TourRestaurant saveTourRestaurant(@PathVariable Long tid,
                                             @PathVariable Long rid){
        return tourRestaurantService.saveTourRestaurant(tid, rid);
    }

    @DeleteMapping("/tours/{tid}/restaurants/{rid}")
    public TourRestaurant deleteTourRestaurant(@PathVariable Long tid,
                                               @PathVariable Long rid){
        return tourRestaurantService.deleteTourRestaurant(tid, rid);
    }
}
