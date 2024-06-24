package com.tours.tourisme.service;

import com.tours.tourisme.model.exception.NotFoundException;
import com.tours.tourisme.model.exception.ResourceAlreadyExistsException;
import com.tours.tourisme.repository.TourInterestPointRepository;
import com.tours.tourisme.repository.entity.InterestPoint;
import com.tours.tourisme.repository.entity.Tour;
import com.tours.tourisme.repository.entity.TourInterestPoint;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TourInterestPointService {
    private final TourInterestPointRepository repository;
    private final TourService tourService;
    private final InterestPointService interestPointService;

    public List<InterestPoint> getAllInterestPoint(Long tid){
        return repository.findAllInterestPointByTourId(tid);
    }

    public TourInterestPoint saveTourInterestPoint(Long tid , Long ipid){  //tid : tour id and ipid : interest point id
        Tour tour = tourService.getTourById(tid);
        InterestPoint interestPoint = interestPointService.getInterestPointById(ipid);
        Optional<TourInterestPoint> existingTourInterestPoint = repository.findByTourIdAndInterestPointId(tid, ipid);

        if(existingTourInterestPoint.isPresent()){
            throw new ResourceAlreadyExistsException("Interest point with id " + ipid + " is already added to this tour");
        }

        TourInterestPoint tourInterestPoint = TourInterestPoint.builder()
                .tour(tour)
                .interestPoint(interestPoint)
                .build();
        return repository.save(tourInterestPoint);
    }

    public TourInterestPoint deleteTourInterestPoint(Long tid , Long ipid){
        Optional<TourInterestPoint> tourInterestPointToDelete = repository.findByTourIdAndInterestPointId(tid, ipid);

        if(tourInterestPointToDelete.isPresent()){
            repository.delete(tourInterestPointToDelete.get());
        }else throw new NotFoundException("Interest point with id "+ ipid + " not found");

        return tourInterestPointToDelete.get();
    }
}