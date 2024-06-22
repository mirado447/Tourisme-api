package com.tours.tourisme.service;

import com.tours.tourisme.model.exception.NotFoundException;
import com.tours.tourisme.model.exception.ResourceAlreadyExistsException;
import com.tours.tourisme.repository.TourUserRepository;
import com.tours.tourisme.repository.entity.Tour;
import com.tours.tourisme.repository.entity.TourUser;
import com.tours.tourisme.repository.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TourUserService {
    private final TourUserRepository tourUserRepository;
    private final UserService userService;
    private final TourService tourService;

    public TourUser getTourUserByTourIdAndUserId(Long tid, Long uid){
        TourUser tourUser = tourUserRepository.findByTourIdAndUserId(tid, uid);
        if (tourUser != null){
            return tourUser;
        }else {
            throw new NotFoundException("User with id "+ uid +" in this tour user not found");
        }
    }

    public TourUser saveTourUser(Long tid, Long uid) {
        User user = userService.getUserById(uid);
        Tour tour = tourService.getTourById(tid);
        TourUser existingTourUser = getTourUserByTourIdAndUserId(tid, uid);

        if(existingTourUser != null){
            throw new ResourceAlreadyExistsException("User with id " + uid + " is already added to this tour");
        }

        TourUser tourUser = TourUser.builder()
                .user(user)
                .tour(tour)
                .build();
        return tourUserRepository.save(tourUser);
    }

    public TourUser deleteTourUser(Long tid, Long uid) {
        TourUser existingToDelete = getTourUserByTourIdAndUserId(tid, uid);
        tourUserRepository.delete(existingToDelete);
        return existingToDelete;
    }
}
