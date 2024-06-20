package com.tours.tourisme.endpoint.controller;

import com.tours.tourisme.model.BoundedPageSize;
import com.tours.tourisme.model.PageFromOne;
import com.tours.tourisme.repository.entity.Location;
import com.tours.tourisme.service.LocationService;
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
public class LocationController {
    private final LocationService locationService;

    @GetMapping("/locations")
    public List<Location> getAllLocation(@RequestParam int page,
                                         @RequestParam(value = "page_size") int pageSize){
        PageFromOne pageFromOne = new PageFromOne(page);
        BoundedPageSize boundedPageSize = new BoundedPageSize(pageSize);
        return locationService.getAllLocation(pageFromOne, boundedPageSize);
    }

    @GetMapping("/locations/{lid}")
    public Location getLocationById(@PathVariable Long lid){
        return locationService.getLocationById(lid);
    }

    @PostMapping("/locations")
    public Location saveLocation(@RequestBody Location location){
        return locationService.saveLocation(location);
    }
}
