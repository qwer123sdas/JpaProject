package com.example.jpaproject.repository;

import com.example.jpaproject.domain.Photo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class PhotoRepository {
    private final EntityManager em;

    public void save(Photo photo){
        em.persist(photo);
    }


}
