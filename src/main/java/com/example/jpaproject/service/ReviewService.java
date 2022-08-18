package com.example.jpaproject.service;

import com.example.jpaproject.domain.*;
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
    private final MileageRepository mileageRepository;
    private final PhotoRepository photoRepository;

    /*
    * 첫 리뷰 저장
    * */
    @Transactional
    public void saveReview(ReviewDto reviewDto){
/*        if(reviewRepository.exitReview(reviewDto) == null){
            // throw
        }*/
        // 포인트 계산
        int points = calculatePoints(reviewDto);

        // 엔티티 조회
        //Place place = placeRepository.findOne(reviewDto.getPlaceId());
        Users users = userRepository.findOne(reviewDto.getUserId());

        // 리뷰 정보 생성
        Review review = Review.addReview(reviewDto.getUserId(), reviewDto.getPlaceId(), reviewDto.getContent());

        // 리뷰 저장
        reviewRepository.save(review);

        // 사진 정보 생성
       for(UUID attachedPhotoId : reviewDto.getAttachedPhotoIds()){
           Photo attachedPhoto = photoRepository.findById(attachedPhotoId);
           review.addPhoto(attachedPhoto);
       }


        // 마일리지 정보 생성 & 저장
        mileageRepository.save(Mileage.addMileage(users.getId(), points, String.format("Added %d points by registering a review.", points))
        );
    }

    /*
    * 리뷰 수정
    * */
    @Transactional
    public void updateReview(UUID reviewID,ReviewDto reviewDto){
    }


    // 포인트 계산
    private int calculatePoints(ReviewDto reviewDto) {
        int points = 0;
        if (reviewDto.getContent().length() > 0)  points += 1;
        if (reviewDto.getAttachedPhotoIds().size() > 0) points += 1;

        return points;
    }

}
