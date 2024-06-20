package com.tours.tourisme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Tour extends JpaRepository<com.tours.tourisme.entity.Tour, Long> {
}