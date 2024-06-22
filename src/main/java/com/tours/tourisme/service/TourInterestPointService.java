package com.tours.tourisme.service;

import com.tours.tourisme.model.BoundedPageSize;
import com.tours.tourisme.model.PageFromOne;
import com.tours.tourisme.model.exception.ResourceAlreadyExistsException;
import com.tours.tourisme.repository.TourInterestPointRepository;
import com.tours.tourisme.repository.entity.InterestPoint;
import com.tours.tourisme.repository.entity.Tour;
import com.tours.tourisme.repository.entity.TourInterestPoint;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TourInterestPointService {
    private final TourInterestPointRepository repository;
    private final TourService tourService;
    private final InterestPointService interestPointService;

    public List<InterestPoint> getAllInterestPoint(PageFromOne page, BoundedPageSize pageSize, Long tid){
        Pageable pageable = PageRequest.of(page.getValue() - 1, pageSize.getValue());
        return repository.findAllInterestPointByTourId(pageable, tid);
    }

    public TourInterestPoint saveTourInterestPoint(Long tid , Long ipid){  //tid : tour id and ipid : interest point id
        Tour tour = tourService.getTourById(tid);
        InterestPoint interestPoint = interestPointService.getInterestPointById(ipid);
        TourInterestPoint existingTourInterestPoint = repository.findByTourIdAndInterestPointId(tid, ipid);

        if(existingTourInterestPoint != null){
            throw new ResourceAlreadyExistsException("Interest point with id :" + ipid + " is already added to this tour");
        }

        TourInterestPoint tourInterestPoint = TourInterestPoint.builder()
                .tour(tour)
                .interestPoint(interestPoint)
                .build();
        return repository.save(tourInterestPoint);
    }

    public TourInterestPoint deleteTourInterestPoint(Long tid , Long ipid){
        TourInterestPoint tourInterestPointToDelete = repository.findByTourIdAndInterestPointId(tid, ipid);
        repository.delete(tourInterestPointToDelete);
        return tourInterestPointToDelete;
    }
}
//TODO: add exception for not found entity if not present or already deleted