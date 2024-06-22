package com.tours.tourisme.repository;

import com.tours.tourisme.repository.entity.TourUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TourUserRepository extends JpaRepository<TourUser, Long> {
    @Query("select tu from TourUser tu where tu.tour.id = :tid and  tu.user.id = :uid")
    TourUser findByTourIdAndUserId(Long tid, Long uid);
}
