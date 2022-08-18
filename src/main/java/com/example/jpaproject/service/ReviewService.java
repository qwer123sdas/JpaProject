package com.example.jpaproject.service;

import com.example.jpaproject.domain.Mileage;
import com.example.jpaproject.domain.Place;
import com.example.jpaproject.domain.Review;
import com.example.jpaproject.domain.User;
import com.example.jpaproject.dto.ReviewDto;
import com.example.jpaproject.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final PhotoService photoService;
    private final ReviewRepository reviewRepository;
    private final PlaceRepository placeRepository;
    private final UserRepository userRepository;

    /*
    * 첫 리뷰 저장
    * */
    @Transactional
    public void saveReview(ReviewDto reviewDto){
        if(reviewRepository.exitReview(reviewDto) == null){
            // throw
        }

        // 엔티티 조회
        Place place = placeRepository.findOne(reviewDto.getPlaceId());
        User user = userRepository.findOne(reviewDto.getUserId());

        // 리뷰 정보 생성
        Review review = Review.createReview(reviewDto.getContent(), reviewDto.getAction(), user, place);

        // 리뷰 저장
        reviewRepository.save(review);

        // 사진 정보 생성
        if(!reviewDto.getAttachedPhotoIds().isEmpty()){
            photoService.save(reviewDto.getAttachedPhotoIds(), review.getId());
        }

        // 마일리지 정보 생성
    }

    /*
    * 리뷰 수정
    * */
    @Transactional
    public void updateReview(UUID reviewID,ReviewDto reviewDto){
    }

}
