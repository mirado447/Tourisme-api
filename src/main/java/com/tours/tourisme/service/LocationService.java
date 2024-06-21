package com.tours.tourisme.service;

import com.tours.tourisme.model.BoundedPageSize;
import com.tours.tourisme.model.PageFromOne;
import com.tours.tourisme.model.exception.NotFoundException;
import com.tours.tourisme.repository.LocationRepository;
import com.tours.tourisme.repository.entity.Location;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LocationService {
    private final LocationRepository repository;

    public List<Location> getAllLocation(PageFromOne page, BoundedPageSize pageSize){
        Pageable pageable = PageRequest.of(page.getValue() - 1, pageSize.getValue());
        return repository.findAllLocation(pageable);
    }

    public Location getLocationById(Long lid){
        return repository
                .findById(lid)
                .orElseThrow(() -> new NotFoundException("Location with id " + lid + " not found"));
    }

    public Location saveLocation(Location location){
        return repository.save(location); //update if the entity already exists
    }
}
