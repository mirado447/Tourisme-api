package com.tours.tourisme.service;

import com.tours.tourisme.model.BoundedPageSize;
import com.tours.tourisme.model.PageFromOne;
import com.tours.tourisme.model.exception.NotFoundException;
import com.tours.tourisme.repository.entity.Tour;
import com.tours.tourisme.repository.TourRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TourService {
    private final TourRepository repository;
    private final ItineraryService itineraryService;
    private final UserService userService;

    public List<Tour> getAllTour(PageFromOne page, BoundedPageSize pageSize){
        Pageable pageable = PageRequest.of(page.getValue() - 1, pageSize.getValue());
        return repository.findAllTour(pageable);
    }

    public Tour saveTour(Tour tour, Long iid){
        tour.setItinerary(itineraryService.getItineraryById(iid));
        tour.setUser(userService.getUserById(tour.getUser().getId()));
        return repository.save(tour);
    }

    public Tour getTourById(Long tid){
        return repository
                .findById(tid)
                .orElseThrow(()-> new NotFoundException("Tour with id "+ tid +" not found"));
    }

    public Tour crupdateTour(Long tid, Tour tour, Long iid){
        Optional<Tour> optionalTour = repository.findById(tid);
        if(optionalTour.isPresent()){
            Tour tourFromDomain = optionalTour.get();
            tour.setId(tourFromDomain.getId());
            tour.setItinerary(itineraryService.getItineraryById(iid));
            tour.setUser(userService.getUserById(tour.getUser().getId()));
            return repository.save(tour);
        }else {
            return saveTour(tour,iid);
        }
    }

    public Tour deleteTourById(Long tid){
        Tour tourToDelete = getTourById(tid);
        repository.delete(tourToDelete);
        return tourToDelete;
    }
}
