package com.example.jpaproject.repository;

import com.example.jpaproject.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

@Repository
public class MemberRepository {
    @Autowired
    EntityManager em;

    public User findOne(UUID userId){
        return em.find(User.class, userId);
    }


}
