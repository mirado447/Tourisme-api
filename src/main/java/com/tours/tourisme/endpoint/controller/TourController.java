package com.tours.tourisme.endpoint.controller;

import com.tours.tourisme.model.BoundedPageSize;
import com.tours.tourisme.model.PageFromOne;
import com.tours.tourisme.repository.entity.Tour;
import com.tours.tourisme.service.TourService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class TourController {
    private final TourService tourService;
    @GetMapping("/tours")
    public List<Tour> getAllTour(@RequestParam int page,
                                 @RequestParam(value = "page_size") int pageSize){
        PageFromOne pageFromOne = new PageFromOne(page);
        BoundedPageSize boundedPageSize = new BoundedPageSize(pageSize);
        return tourService.getAllTour(pageFromOne, boundedPageSize);
    }
    @PostMapping("/tours")
    public Tour saveTour(@RequestBody Tour tour){
        return tourService.saveTour(tour);
    }
}
