package com.example.jpaproject.repository;

import com.example.jpaproject.domain.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;

    public void save(Users users){
        em.persist(users);
    }

    public Users findOne(UUID userId){
        return em.find(Users.class, userId);
    }


}
