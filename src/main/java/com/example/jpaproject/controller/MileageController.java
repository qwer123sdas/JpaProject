package com.example.jpaproject.controller;

import com.example.jpaproject.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MileageController {

    private final ReviewService reviewService;

    @GetMapping("mileages/{userId}")
    public void showMileages(@PathVariable String userId,
                                         @RequestParam(name="page",defaultValue="0") int page) {

    }
}
