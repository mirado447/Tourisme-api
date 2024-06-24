package com.tours.tourisme.repository;

import com.tours.tourisme.repository.entity.InterestPoint;
import com.tours.tourisme.repository.entity.TourInterestPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TourInterestPointRepository extends JpaRepository<TourInterestPoint, Long> {
    @Query("select  tip from TourInterestPoint tip where tip.tour.id = :tid and  tip.interestPoint.id = :ipid")
    Optional<TourInterestPoint> findByTourIdAndInterestPointId(Long tid, Long ipid);

    @Query("select tip.interestPoint from TourInterestPoint tip where tip.tour.id = :tid ")
    List<InterestPoint> findAllInterestPointByTourId(Long tid);
}
