package com.tours.tourisme.endpoint.controller;

import com.tours.tourisme.repository.entity.TourUser;
import com.tours.tourisme.service.TourUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class TourUserController {
    private final TourUserService tourUserService;

    @PostMapping("/tours/{tid}/users/{uid}/tour_user")
    public TourUser saveTourUser(@PathVariable Long tid,
                                 @PathVariable Long uid) {
        return tourUserService.saveTourUser(uid, tid);
    }

    @DeleteMapping("/tours/{tid}/users/{uid}/tour_user")
    public TourUser deleteTourUser(@PathVariable Long tid,
                                   @PathVariable Long uid) {
        return tourUserService.deleteTourUser(tid, uid);
    }
}
