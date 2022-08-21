package com.example.jpaproject.service;

import com.example.jpaproject.domain.Mileage;
import com.example.jpaproject.domain.Review;
import com.example.jpaproject.domain.Users;
import com.example.jpaproject.repository.MileageRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MileageService {

    private final MileageRepository mileageRepository;

    @Transactional
    public void addPoints(Users users, Review review) {
        int points = calculatePoints(review);

        users.setPoint(points);

        Mileage mileage = Mileage.builder()
                        .users(users)
                        .point(points)
                        .reason(String.format(
                            "Added %d points by registering a review.",
                            points))
                        .build();

        mileageRepository.save(mileage);
    }

    public void updatePoint(Users users, Review review) {
        //mileageRepository.findOne();

    }


    // 포인트 계산
    private int calculatePoints(Review review) {
        int points = 0;
        System.out.println(!StringUtils.isBlank(review.getContent()));
        System.out.println(review.getAttachedPhotos().size() > 0);
        System.out.println(review.isFirst());
        if (!StringUtils.isBlank(review.getContent()))  points += 1;
        if (review.getAttachedPhotos().size() > 0) points += 1;
        if (review.isFirst()) points += 1;
        return points;
    }


}
