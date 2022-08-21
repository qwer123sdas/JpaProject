package com.example.jpaproject.service;
import com.example.jpaproject.domain.*;
import com.example.jpaproject.dto.ReviewDto;
import com.example.jpaproject.repository.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ReviewServiceTest {

    @Autowired
    ReviewService reviewService;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    MileageRepository mileageRepository;
    @Autowired
    PhotoRepository photoRepository;
    @Autowired
    PlaceRepository placeRepository;
    @Autowired
    UserRepository userRepository;

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

    @Test
    public void 테스트_저장1() throws Exception{
        //given
        UUID userId = UUID.randomUUID();
        userRepository.save(user.builder().id(userId).build());
        UUID placeId = UUID.randomUUID();
        placeRepository.save(place.builder().id(placeId).build());

        List<Photo> photos = Arrays.asList(p1.builder().attachedPhotoId(UUID.randomUUID()).build(),
                p2.builder().attachedPhotoId(UUID.randomUUID()).build());


        List<UUID> attachedPhotoIds = photos.stream().map(Photo::getId).collect(Collectors.toList());

        ReviewDto reviewDto = ReviewDto.builder()
                            .reviewId(UUID.randomUUID())
                            .placeId(placeId)
                            .userId(userId)
                            .attachedPhotoIds(attachedPhotoIds)
                            .content("좋았습니다!")
                            .build();

        //when
        Review registerd = reviewService.registerReview(reviewDto);

        Users user = userRepository.findOne(userId); //DataIntegrityViolationException

        //then
        int result = user.getTotalPoint();
        int reviewPoint = registerd.getPoint();

        assertThat(result).isEqualTo(3);
        assertThat(reviewPoint).isEqualTo(3);
        assertThat(registerd.getContent()).isEqualTo(reviewDto.getContent());
        // assertThat(registerd.getAttachedPhotos()).containsExactly(photos.get(1), photos.get(0));
    }

    @Test
    public void 게시글_수정1() throws Exception{
        //given
        UUID userId = UUID.randomUUID();
        UUID placeId = UUID.randomUUID();
        UUID reviewId = UUID.randomUUID();
        List<Photo> photos = Arrays.asList(p1.builder().attachedPhotoId(UUID.randomUUID()).build(),
                p2.builder().attachedPhotoId(UUID.randomUUID()).build());
        List<UUID> attachedPhotoIds = photos.stream().map(Photo::getId).collect(Collectors.toList());

        userRepository.save(user.builder().id(userId).build());
        placeRepository.save(place.builder().id(placeId).build());

            // Write first review : 3 points
        Review registerd = reviewService.registerReview(ReviewDto.builder()
                .reviewId(reviewId)
                .userId(userId)
                .placeId(placeId)
                .attachedPhotoIds(attachedPhotoIds)
                .content("first")
                .build()
        );
            // Update review with new images
        ReviewDto modifyDto = ReviewDto.builder()
                .reviewId(reviewId)
                .userId(userId)
                .placeId(placeId)
                .attachedPhotoIds(new ArrayList<>())
                .content("modify")
                .build();

        //when
        reviewService.updateReview(registerd.getId(), modifyDto);
        Users user = userRepository.findOne(userId);

        //then
        int totalPoint = user.getTotalPoint();
        int reviewPoint = registerd.getPoint();

        assertThat(totalPoint).isEqualTo(2);
        assertThat(reviewPoint).isEqualTo(2);
        assertThat("modify").isEqualTo(modifyDto.getContent());

    }

}