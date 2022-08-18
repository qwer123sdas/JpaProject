package com.example.jpaproject.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter
public class ReviewDto {

    private String content;
    private String action;
    private UUID userId;
    private UUID placeId;



    // == 생성자 메서드 == //
    public void createUpdateReviewDto(String content, String action){
        this.content = content;
        this.action  = action;
    }
}
