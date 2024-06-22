package com.tours.tourisme.endpoint.controller;

import com.tours.tourisme.repository.entity.InterestPoint;
import com.tours.tourisme.repository.entity.TourInterestPoint;
import com.tours.tourisme.service.TourInterestPointService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class TourInterestPointController {
    private final TourInterestPointService tourInterestPointService;

    @GetMapping("/tours/{tid}/interest_point")
    public List<InterestPoint> getAllInterestPoint(@PathVariable Long tid){
        return tourInterestPointService.getAllInterestPoint(tid);
    }

    @PostMapping("/tours/{tid}/interest_point/{ipid}")
    public TourInterestPoint saveTourInterestPoint(@PathVariable Long tid,
                                                   @PathVariable Long ipid){
        return tourInterestPointService.saveTourInterestPoint(tid, ipid);
    }

    @DeleteMapping("/tours/{tid}/interest_point/{ipid}")
    public TourInterestPoint deleteTourInterestPoint(@PathVariable Long tid,
                                                   @PathVariable Long ipid){
        return tourInterestPointService.deleteTourInterestPoint(tid, ipid);
    }
}
