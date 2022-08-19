package com.example.jpaproject.service;

import com.example.jpaproject.domain.*;
import com.example.jpaproject.dto.ReviewDto;
import com.example.jpaproject.repository.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final MileageService mileageService;
    private final PhotoService photoService;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final PhotoRepository photoRepository;
    private final PlaceRepository placeRepository;


    /*
    * 첫 리뷰 저장
    * */
    @Transactional
    public Review registerReview(ReviewDto reviewDto){
/*        if(reviewRepository.exitReview(reviewDto) == null){
            // throw
        }*/

        // == 엔티티 조회 == //
        Users users = userRepository.findOne(reviewDto.getUserId());
        Place place = placeRepository.findById(reviewDto.getPlaceId()).orElseThrow(RuntimeException::new);
        List<Photo> attachedPhotos = photoRepository.findAllById(reviewDto.getAttachedPhotoIds());
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
 /*       for(UUID attachedPhotoId : reviewDto.getAttachedPhotoIds()){
            Photo attachedPhoto = photoRepository.save(attachedPhotoId);
            review.attachPhotos(attachedPhoto);
        }*/


       // 마일리지
        mileageService.updatePoints(users, review);

        // 사진
       // photoService.attachPhoto(reviewDto.getAttachedPhotoIds(), review.getId());

        // == 저장 == //
       return reviewRepository.save(review);
    }

    /*
    * 리뷰 수정
    * */
    @Transactional
    public void updateReview(UUID reviewID,ReviewDto reviewDto){
    }




/*    private boolean isFirstReviewAtPlace(UUID placeId, Review review){
        return reviewRepository.existsBy
    }*/

}
