package com.example.jpaproject.repository;

import com.example.jpaproject.domain.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.UUID;


public interface PlaceRepository extends JpaRepository<Place, UUID> {

}
