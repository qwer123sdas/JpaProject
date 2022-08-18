package com.example.jpaproject.service;
import com.example.jpaproject.domain.Place;
import com.example.jpaproject.domain.Review;
import com.example.jpaproject.repository.PhotoRepository;
import com.example.jpaproject.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ReviewServiceTest {


    @Autowired
    EntityManager em;
    @Autowired
    ReviewService reviewService;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    PhotoRepository photoRepository;

    public void savePlace(){
        Place place =

    }
    public void saveUser(){

    }
    @Test
    public void saveReview() {
        //given
        Review review = Review.createReview()
        //when

        //then

    }

}