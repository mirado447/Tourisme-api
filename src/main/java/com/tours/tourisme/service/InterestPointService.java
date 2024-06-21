package com.tours.tourisme.service;

import com.tours.tourisme.model.BoundedPageSize;
import com.tours.tourisme.model.PageFromOne;
import com.tours.tourisme.model.exception.NotFoundException;
import com.tours.tourisme.repository.InterestPointRepository;
import com.tours.tourisme.repository.entity.InterestPoint;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InterestPointService {
    private final InterestPointRepository repository;
    private final LocationService locationService;
    public List<InterestPoint> getAllInterestPoint(PageFromOne page, BoundedPageSize pageSize){
        Pageable pageable = PageRequest.of(page.getValue() - 1, pageSize.getValue());
        return repository.findAllInterestPoint(pageable);
    }

    public InterestPoint getInterestPointById(Long ipid){
        return repository
                .findById(ipid)
                .orElseThrow(() -> new NotFoundException("Interest point with id "+ ipid + " not found"));
    }

    public InterestPoint saveInterestPoint(InterestPoint interestPoint){
        interestPoint.setLocation(locationService.getLocationById(interestPoint.getLocation().getId()));
        return repository.save(interestPoint);
    }

    public InterestPoint crupdateInterestPoint(Long ipid, InterestPoint interestPoint){
        Optional<InterestPoint> optionalInterestPoint = repository.findById(ipid);
        if(optionalInterestPoint.isPresent()){
            InterestPoint interestPointToUpdate = optionalInterestPoint.get();
            interestPointToUpdate.setName(interestPoint.getName());
            interestPointToUpdate.setDescription(interestPoint.getDescription());
            interestPointToUpdate.setLocation(locationService.getLocationById(interestPoint.getLocation().getId()));
            interestPointToUpdate.setCost(interestPoint.getCost());
            return repository.save(interestPointToUpdate);
        }else {
            return saveInterestPoint(interestPoint);
        }
    }
}
