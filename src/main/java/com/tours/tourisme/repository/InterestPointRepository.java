package com.tours.tourisme.repository;

import com.tours.tourisme.repository.entity.InterestPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestPointRepository extends JpaRepository<InterestPoint,Long> {
}
