package com.tours.tourisme.endpoint.controller;

import com.tours.tourisme.model.BoundedPageSize;
import com.tours.tourisme.model.PageFromOne;
import com.tours.tourisme.repository.entity.Itinerary;
import com.tours.tourisme.service.ItineraryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ItineraryController {
    private final ItineraryService itineraryService;

    @GetMapping("/itineraries")
    public List<Itinerary> getAllItinerary(@RequestParam int page,
                                           @RequestParam(value = "page_size") int pageSize){
        PageFromOne pageFromOne = new PageFromOne(page);
        BoundedPageSize boundedPageSize = new BoundedPageSize(pageSize);
        return itineraryService.getAllItinerary(pageFromOne, boundedPageSize);
    }

    @GetMapping("/itineraries/{iid}")
    public Itinerary getItineraryById(@PathVariable Long iid){
        return itineraryService.getItineraryById(iid);
    }

    @PostMapping("/itineraries")
    public Itinerary saveItinerary(@RequestBody Itinerary itinerary){
        return itineraryService.saveItinerary(itinerary);
    }
}
