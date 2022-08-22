package com.example.jpaproject.controller;

import com.example.jpaproject.dto.ReviewDto;
import com.example.jpaproject.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping (path="/events", produces="text/plain;charset=UTF-8")
    public void insertReview(@RequestBody ReviewDto reviewDto){
        if(reviewDto.getAction().equals("ADD")){
            reviewService.registerReview(reviewDto);
        }else if(reviewDto.getAction().equals("MODIFY")){
            reviewService.updateReview(reviewDto.getReviewId(), reviewDto);
        }else if(reviewDto.getAction().equals("DELETE")){
            reviewService.deleteReview(reviewDto.getReviewId());
        }
    }
}
