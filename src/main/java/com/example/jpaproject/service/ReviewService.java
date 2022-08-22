package com.example.jpaproject.service;

import com.example.jpaproject.domain.*;
import com.example.jpaproject.dto.ReviewDto;
import com.example.jpaproject.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final MileageService mileageService;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final PhotoRepository photoRepository;
    private final PlaceRepository placeRepository;


    /*
    * 첫 리뷰 저장
    * */
    @Transactional
    public Review registerReview(ReviewDto reviewDto){
        // == 엔티티 조회 == //
        Users users = userRepository.findOne(reviewDto.getUserId());
        Place place = placeRepository.findById(reviewDto.getPlaceId()).orElseThrow(RuntimeException::new);
        List<Photo> attachedPhotos = photoRepository.findAllById(reviewDto.getAttachedPhotoIds());

        // == 리뷰 중복 검사 == //
        validateFirstReviewByUser(place, users);

        // == 정보 생성 == //
        Review review = Review.builder()
                .users(users)
                .place(place)
                .attachedPhotos(attachedPhotos)
                .content(reviewDto.getContent())
                .status(ReviewStatus.ADD)
                .first(!reviewRepository.existsByPlace(place))
                .build();
        review.addAttachPhotos(reviewDto.getAttachedPhotoIds());

        // 마일리지
        mileageService.addPoints(users, review);
        // == 저장 == //
       return reviewRepository.save(review);
    }

    private void validateFirstReviewByUser(Place place, Users users){
        if(reviewRepository.existsByPlaceAndAndUsers(place, users)){
            throw new IllegalStateException("이미 작성한 리뷰가 있습니다.");
        }
    }

    /*
    * 리뷰 수정
    * */
    @Transactional
    public void updateReview(UUID reviewId,ReviewDto reviewDto){
        // == 엔티티 조회 == //
        Users users = userRepository.findOne(reviewDto.getUserId());
        Review review = reviewRepository.findById(reviewId).orElseThrow(RuntimeException::new);
        List<Photo> attachedPhotos = photoRepository.findAllById(reviewDto.getAttachedPhotoIds());

        // == 엔티티 수정 == //
        review.update(reviewDto.getContent(), attachedPhotos, ReviewStatus.MODIFY);

        // 마일리지
        mileageService.updatePoint(users, review);
    }

    @Transactional
    public void deleteReview(UUID reviewId){
        mileageService.withdrawPoints(reviewId);
        reviewRepository.deleteById(reviewId);
    }




}
