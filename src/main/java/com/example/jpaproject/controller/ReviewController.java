package com.example.jpaproject.controller;

import com.example.jpaproject.dto.ReviewDto;
import com.example.jpaproject.repository.ReviewRepository;
import com.example.jpaproject.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping(path="events", produces="text/plain;charset=UTF-8")
    public void insertReview(@RequestBody ReviewDto reviewDto){
        if(reviewDto.getStatus().equals("ADD")){
            reviewService.registerReview(reviewDto);
        }else if(reviewDto.getStatus().equals("MODIFY")){
            reviewService.updateReview(reviewDto);
        }else if(reviewDto.getStatus().equals("DELETE")){
            reviewService.deleteReview(reviewDto.getReviewId());
        }
    }
}
