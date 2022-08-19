package com.example.jpaproject.repository;

import com.example.jpaproject.domain.Photo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;



public interface PhotoRepository extends JpaRepository<Photo, UUID>{

}
