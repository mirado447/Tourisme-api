package com.tours.tourisme.service;

import com.tours.tourisme.model.BoundedPageSize;
import com.tours.tourisme.model.PageFromOne;
import com.tours.tourisme.model.exception.NotFoundException;
import com.tours.tourisme.repository.ItineraryRepository;
import com.tours.tourisme.repository.entity.Itinerary;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ItineraryService {
    private final ItineraryRepository repository;
    private final LocationService locationService;

    public List<Itinerary> getAllItinerary(PageFromOne page, BoundedPageSize pageSize){
        Pageable pageable = PageRequest.of(page.getValue() - 1, pageSize.getValue());
        return repository.findAllItinerary(pageable);
    }

    public Itinerary getItineraryById(Long iid){
        return repository
                .findById(iid)
                .orElseThrow(() -> new NotFoundException("Itinerary with id "+ iid + " not found"));
    }

    public Itinerary saveItinerary(Itinerary itinerary){
        itinerary.setStart_location(locationService.getLocationById(itinerary.getStart_location().getId()));
        itinerary.setEnd_location(locationService.getLocationById(itinerary.getEnd_location().getId()));
        return repository.save(itinerary);
    }
}
