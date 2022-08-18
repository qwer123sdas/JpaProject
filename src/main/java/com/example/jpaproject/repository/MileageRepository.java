package com.example.jpaproject.repository;

import com.example.jpaproject.domain.Mileage;
import com.example.jpaproject.domain.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class MileageRepository {

    private final EntityManager em;

    /*
    * 마일리지 적립
    * */
    public void save(Mileage mileage){
        em.persist(mileage);
    }

    /*
    * 회원 마일리지 총액 가져오기
    * */
    public Mileage findOne(UUID mileageId){
        return em.find(Mileage.class, mileageId);
    }

    public List<Mileage> getAllMileageByUserId(UUID userId) {
        return em.createQuery("select m from Mileage m where m.userId = :userId", Mileage.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    /*
    * 회원 마일리지 로그기록 가져오기
    * */


    // 나중에 여력되면 페이징 처리까지..?

}
