package com.example.jpaproject.repository;

import com.example.jpaproject.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;

    public User findOne(UUID userId){
        return em.find(User.class, userId);
    }


}
