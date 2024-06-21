package com.tours.tourisme.endpoint.controller;

import com.tours.tourisme.model.BoundedPageSize;
import com.tours.tourisme.model.PageFromOne;
import com.tours.tourisme.repository.entity.InterestPoint;
import com.tours.tourisme.service.InterestPointService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class InterestPointController {
    private final InterestPointService interestPointService;

    @GetMapping("/interest_point")
    public List<InterestPoint> getAllInterestPoint(@RequestParam int page,
                                                   @RequestParam(value = "page_size") int pageSize){
        PageFromOne pageFromOne = new PageFromOne(page);
        BoundedPageSize boundedPageSize = new BoundedPageSize(pageSize);
        return interestPointService.getAllInterestPoint(pageFromOne, boundedPageSize);
    }

    @GetMapping("/interest_point/{ipid}")
    public InterestPoint getInterestPointById(@PathVariable Long ipid){
        return interestPointService.getInterestPointById(ipid);
    }

    @PostMapping("/interest_point")
    public InterestPoint saveInterestPoint(@RequestBody InterestPoint interestPoint){
        return interestPointService.saveInterestPoint(interestPoint);
    }

    @PutMapping("/interest_point/{ipid}")
    public InterestPoint crupdateInterestPoint(@PathVariable Long ipid,
                                               @RequestBody InterestPoint interestPoint){
        return interestPointService.crupdateInterestPoint(ipid, interestPoint);
    }
}
