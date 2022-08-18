package com.example.jpaproject.dto;

import com.example.jpaproject.domain.ReviewStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;
import java.util.UUID;

@Getter @Setter
public class ReviewDto {

    private UUID reviewId; // review_id
    private UUID userId;
    private UUID placeId;

    private String content;

    @Enumerated(EnumType.STRING)
    private ReviewStatus status;

    private List<UUID> attachedPhotoIds;


    // == 생성자 메서드 == //
    @Builder
    private ReviewDto(UUID reviewId, UUID userId, UUID placeId, String content, List<UUID> attachedPhotoIds){
        this.reviewId = reviewId;
        this.userId = userId;
        this.placeId = placeId;
        this.content = content;
        this.attachedPhotoIds = attachedPhotoIds;
    }
    public static ReviewDto createReviewDto(UUID userId, UUID placeId, String content){
        ReviewDto review = ReviewDto.builder()
                .userId(userId)
                .placeId(placeId)
                .content(content)
                .build();
        review.reviewId = UUID.randomUUID();
        review.status = ReviewStatus.ADD;
        return review;
    }


}
