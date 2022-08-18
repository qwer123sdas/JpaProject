package com.example.jpaproject.repository;

import com.example.jpaproject.domain.Review;
import com.example.jpaproject.dto.ReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ReviewRepository {
    private final EntityManager em;

    /*
    * 저장
    * */
    public void save(Review review){
        if(review.getId() == null){
            // 신규 등록
            em.persist(review);
        }else{
            // 재 등록  // 근데 comment만 바뀌니 여기서 굳이 merge를 해야 하는지 고민이긴함.
            em.merge(review);
        }
    }



    /*
    *  단건 조회(유저아이디와 장소 아이디로)
    * */

    public Review findOne(UUID reviewId) {
        return em.find(Review.class, reviewId);
    }
/*    public Review exitReview(ReviewDto reviewDto){
        return em.createQuery("select r from Review r " +
                            "where r.place = :place_Id " +
                            "and r.user = :user_Id", Review.class)
                .setParameter("place_Id", reviewDto.getPlaceId())
                .setParameter("user_Id", reviewDto.getUserId())
                .getSingleResult();
    }*/


}
