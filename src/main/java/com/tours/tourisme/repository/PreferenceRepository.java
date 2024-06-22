package com.tours.tourisme.repository;

import com.tours.tourisme.repository.entity.Preference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreferenceRepository extends JpaRepository<Preference, Long> {
   @Query("select p from Preference p where p.user.id = :uid")
   List<Preference> findByUserId(Long uid);
}
