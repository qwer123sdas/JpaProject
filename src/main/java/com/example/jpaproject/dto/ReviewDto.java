package com.example.jpaproject.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter @Setter
public class ReviewDto {

    private String content;
    private String action;
    private UUID userId;
    private UUID placeId;

    private List<UUID> attachedPhotoIds;



    // == 생성자 메서드 == //

    public void createUpdateReviewDto(String content, String action, UUID userId, UUID placeId){
        this.content = content;
        this.action  = action;
        this.userId = userId;
        this.placeId = placeId;
    }
}
