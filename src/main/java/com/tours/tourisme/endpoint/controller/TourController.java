package com.tours.tourisme.endpoint.controller;

import com.tours.tourisme.model.BoundedPageSize;
import com.tours.tourisme.model.PageFromOne;
import com.tours.tourisme.repository.entity.Tour;
import com.tours.tourisme.service.TourService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
public class TourController {
    private final TourService tourService;

    @GetMapping("/tours/{tid}")
    public Tour getTourById(@PathVariable Long tid){
        return tourService.getTourById(tid);
    }

    @GetMapping("/tours")
    public List<Tour> getAllTour(@RequestParam int page,
                                 @RequestParam(value = "page_size") int pageSize){
        PageFromOne pageFromOne = new PageFromOne(page);
        BoundedPageSize boundedPageSize = new BoundedPageSize(pageSize);
        return tourService.getAllTour(pageFromOne, boundedPageSize);
    }

    @GetMapping("/users/{uid}/tours_preferences")
    public Set<Tour> getToursByUserPreferences(@PathVariable Long uid) {
        return tourService.getTourByUserPreferences(uid);
    }

    @PostMapping("/tours")
    public Tour saveTour(@RequestBody Tour tour){
        return tourService.saveTour(tour);
    }

    @PutMapping("/tours/{tid}")
    public Tour crupdateTour(@PathVariable Long tid,
                             @RequestBody Tour tour){
        return tourService.crupdateTour(tid, tour);
    }

    @DeleteMapping("/tours/{tid}")
    public Tour deleteTourById(@PathVariable Long tid){
        return tourService.deleteTourById(tid);
    }
}
