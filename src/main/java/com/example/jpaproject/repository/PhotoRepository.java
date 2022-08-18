package com.example.jpaproject.repository;

import com.example.jpaproject.domain.Photo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PhotoRepository {
    private final EntityManager em;

    public void save(Photo photo){
        em.persist(photo);
    }

    public void saveAll(List<Photo> photos){
        for(Photo photo : photos){
            em.persist(photo);
        }
    }

    public Photo findById(UUID attachedPhotoId) {
        return em.find(Photo.class, attachedPhotoId);
    }
}
