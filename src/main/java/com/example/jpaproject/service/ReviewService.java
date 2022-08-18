package com.example.jpaproject.service;

import com.example.jpaproject.domain.Review;
import com.example.jpaproject.dto.ReviewDto;
import com.example.jpaproject.repository.AttachedPhotoRepository;
import com.example.jpaproject.repository.MemberRepository;
import com.example.jpaproject.repository.MileageRepository;
import com.example.jpaproject.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;

    /*
    * 첫 리뷰 저장
    * */
    @Transactional
    public void saveReview(ReviewDto reviewDto){
        if(reviewRepository.exitReview(reviewDto) == null){
            // throw
        }
        Review review = Review.builder()
                        .content(reviewDto.getContent())
                        .action(reviewDto.getAction())
                        .build();
        reviewRepository.save(review);
    }

    /*
    * 리뷰 수정
    * */
    @Transactional
    public void updateReview(UUID reviewID,ReviewDto reviewDto){
    }

}
