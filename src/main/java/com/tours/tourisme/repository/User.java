package com.tours.tourisme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface User extends JpaRepository<com.tours.tourisme.entity.User, Long> {
}
