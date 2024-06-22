package com.tours.tourisme.service;

import com.tours.tourisme.model.BoundedPageSize;
import com.tours.tourisme.model.PageFromOne;
import com.tours.tourisme.model.exception.NotFoundException;
import com.tours.tourisme.repository.PreferenceRepository;
import com.tours.tourisme.repository.entity.Location;
import com.tours.tourisme.repository.entity.Preference;
import com.tours.tourisme.repository.entity.Tour;
import com.tours.tourisme.repository.TourRepository;
import com.tours.tourisme.repository.entity.User;
import com.tours.tourisme.service.utils.CostService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class TourService {
    private final TourRepository repository;
    private final ItineraryService itineraryService;
    private final UserService userService;
    private final PreferenceRepository preferenceRepository;
    private final CostService costService;

    public List<Tour> getAllTour(PageFromOne page, BoundedPageSize pageSize){
        Pageable pageable = PageRequest.of(page.getValue() - 1, pageSize.getValue());
        return repository.findAllTour(pageable);
    }

    public Tour saveTour(Tour tour, Long iid){
        tour.setItinerary(itineraryService.getItineraryById(iid));
        return repository.save(tour);
    }

    public Tour getTourById(Long tid){
        Tour tour = repository
                .findById(tid)
                .orElseThrow(()-> new NotFoundException("Tour with id "+ tid +" not found"));
        tour.setCost(costService.totalCost(tid));
        return tour;
    }

    public Tour crupdateTour(Long tid, Tour tour, Long iid){
        Optional<Tour> optionalTour = repository.findById(tid);
        if(optionalTour.isPresent()){
            Tour tourFromDomain = optionalTour.get();
            tour.setId(tourFromDomain.getId());
            tour.setItinerary(itineraryService.getItineraryById(iid));
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

    public Set<Tour> getTourByUserPreferences(Long uid){
        User user = userService.getUserById(uid);
        List<Preference> preferences = preferenceRepository.findByUserId(user.getId());
        Set<Tour> tours = new HashSet<>();

        for (Preference preference : preferences) {
            BigDecimal maxBudget = preference.getMax_budget();
            int maxDuration = preference.getMax_duration();
            Location startLocation = preference.getStart_location();
            Location endLocation = preference.getEnd_location();

            List<Tour> toursForPreference = repository.findToursByPreferences(
                    maxBudget, maxDuration, startLocation, endLocation);
            tours.addAll(toursForPreference);
        }
        return tours;
    }
}
