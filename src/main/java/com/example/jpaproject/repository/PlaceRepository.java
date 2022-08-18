package com.example.jpaproject.repository;

import com.example.jpaproject.domain.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PlaceRepository {
    private final EntityManager em;

    public Place findOne(UUID placeId){
        return em.find(Place.class, placeId);
    }
}
