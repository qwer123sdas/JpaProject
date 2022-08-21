package com.example.jpaproject.service;

import com.example.jpaproject.domain.Mileage;
import com.example.jpaproject.domain.Review;
import com.example.jpaproject.domain.Users;
import com.example.jpaproject.repository.MileageRepository;
import com.example.jpaproject.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class MileageService {

    private final MileageRepository mileageRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public void addPoints(Users users, Review review) {
        int points = calculatePoints(review);

        users.setTotalPoint(users.getTotalPoint() + points);
        review.setPoint(points);

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
        int modifiedPoint = calculatePoints(review); // 2
        int diff = modifiedPoint - users.getTotalPoint(); // 2 - 3 = -1

        if(diff == 0){
            return;
        }

        users.setTotalPoint(users.getTotalPoint() + diff);
        review.setPoint(modifiedPoint);

        String verb = diff > 0 ? "Added": "Withdrew";
        Mileage mileage = Mileage.builder()
                .users(users)
                .point(diff)
                .reason(String.format(
                        "%s %d points by registering a review.",
                        verb,
                        diff))
                .build();


        mileageRepository.save(mileage);
    }

    public void withdrawPoints(UUID reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(RuntimeException::new);
        Users user = review.getUsers();

        user.setTotalPoint(user.getTotalPoint() - review.getPoint());

        Mileage mileage = Mileage.builder()
                        .users(user)
                        .point(-review.getPoint())
                        .reason(String.format(
                                "Withdrew %d points by deleting a review.",
                                review.getPoint()
                        ))
                        .build();
        mileageRepository.save(mileage);
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
