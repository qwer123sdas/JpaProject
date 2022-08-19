package com.example.jpaproject.repository;

import com.example.jpaproject.domain.Place;
import com.example.jpaproject.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface ReviewRepository extends JpaRepository<Review, UUID> {
    boolean existsByPlace(Place place);
}
