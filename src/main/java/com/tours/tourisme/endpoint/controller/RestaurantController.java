package com.tours.tourisme.endpoint.controller;

import com.tours.tourisme.model.BoundedPageSize;
import com.tours.tourisme.model.PageFromOne;
import com.tours.tourisme.repository.entity.Restaurant;
import com.tours.tourisme.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public List<Restaurant> getAllRestaurant(@RequestParam int page,
                                            @RequestParam(value = "page_size") int pageSize){
        PageFromOne pageFromOne = new PageFromOne(page);
        BoundedPageSize boundedPageSize = new BoundedPageSize(pageSize);
        return restaurantService.getAllRestaurant(pageFromOne, boundedPageSize);
    }

    @GetMapping("/restaurants/{rid}")
    public Restaurant getRestaurantById(@PathVariable Long rid){
        return restaurantService.getRestaurantById(rid);
    }

    @PostMapping("/restaurants")
    public Restaurant saveRestaurant(@RequestBody Restaurant restaurant){
        return restaurantService.saveRestaurant(restaurant);
    }

    @PutMapping("/restaurants/{rid}")
    public Restaurant crupdateRestaurant(@PathVariable Long rid,
                                         @RequestBody Restaurant restaurant){
        return restaurantService.crupdateRestaurant(rid, restaurant);
    }
}
