package com.example.jpaproject.dto;

import com.example.jpaproject.domain.ReviewStatus;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ReviewDto {
    @NotNull(message = "리뷰아이디가 없습니다.")
    private UUID reviewId;
    @NotNull(message = "유저아이디가 없습니다.")
    private UUID userId;
    @NotNull(message = "해당 장소가 없습니다.")
    private UUID placeId;

    @Enumerated(EnumType.STRING)
    private ReviewStatus action;
    @NotBlank(message = "공백만 입력할 수 없습니다.")
    private String content;
    private List<UUID> attachedPhotoIds;


    // == 생성자 메서드 == //
    @Builder
    private ReviewDto(UUID reviewId, UUID userId, UUID placeId,
                      String content, ReviewStatus action, List<UUID> attachedPhotoIds){
        this.reviewId = reviewId;
        this.userId = userId;
        this.placeId = placeId;
        this.content = content;
        this.action = action;
        this.attachedPhotoIds = attachedPhotoIds;
    }
    public static ReviewDto createReviewDto(UUID userId, UUID placeId, String content){
        ReviewDto review = ReviewDto.builder()
                .userId(userId)
                .placeId(placeId)
                .content(content)
                .build();
        review.reviewId = UUID.randomUUID();
        review.action = ReviewStatus.ADD;
        return review;
    }


}
