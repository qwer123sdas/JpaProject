package com.example.jpaproject.dto;

import com.example.jpaproject.domain.Photo;
import com.example.jpaproject.domain.Place;
import com.example.jpaproject.domain.ReviewStatus;
import com.example.jpaproject.domain.Users;
import com.example.jpaproject.repository.PlaceRepository;
import com.example.jpaproject.repository.UserRepository;
import com.example.jpaproject.service.ReviewService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ReviewDtoTest {
    @Autowired
    ReviewService reviewService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PlaceRepository placeRepository;

    private Users user;
    private Place place;
    private Photo p1;
    private Photo p2;

    @BeforeEach
    void setUp() {
        user = new Users();
        place = new Place();
        p1 = new Photo();
        p2 = new Photo();
    }

    @Test(expected = AssertionError.class)
    public void 공백_유효성검사() throws Exception{
        //given
        UUID userId = UUID.randomUUID();
        userRepository.save(user.builder().id(userId).build());
        UUID placeId = UUID.randomUUID();
        placeRepository.save(place.builder().id(placeId).build());

        ReviewDto dto = ReviewDto.builder()
                .reviewId(UUID.randomUUID())
                .placeId(placeId)
                .userId(userId)
                .content(" ")
                .attachedPhotoIds(new ArrayList<>())
                .status(ReviewStatus.ADD)
                .build();

        //when
        reviewService.registerReview(dto);

        //then
        fail("공백만 입력할 수 없습니다.");
    }

}