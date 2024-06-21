package com.tours.tourisme.repository;

import com.tours.tourisme.repository.entity.InterestPoint;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterestPointRepository extends JpaRepository<InterestPoint,Long> {
    @Query("SELECT ip from InterestPoint ip")
    List<InterestPoint> findAllInterestPoint(Pageable pageable);
}
