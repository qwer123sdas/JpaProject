package com.example.jpaproject.service;
import com.example.jpaproject.domain.*;
import com.example.jpaproject.dto.ReviewDto;
import com.example.jpaproject.repository.*;
import org.assertj.core.api.Assertions;
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

    @Test
    public void 테스트_저장1() throws Exception{
        //given
        UUID userId = UUID.randomUUID();
        userRepository.save(Users.builder().id(userId).build());
        UUID placeId = UUID.randomUUID();
        placeRepository.save(Place.builder().id(placeId).build());

        List<Photo> photos = Arrays.asList(Photo.builder().attachedPhotoId(UUID.randomUUID()).build(),
                                            Photo.builder().attachedPhotoId(UUID.randomUUID()).build());


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
        List<Mileage> mileageList = mileageRepository.getAllMileageByUserId(user.getId());
        int result = mileageList.stream()
                        .mapToInt(Mileage::getPoint)
                                .sum();
        assertThat(result).isEqualTo(3);
        assertThat(registerd.getContent()).isEqualTo(reviewDto.getContent());
        // assertThat(registerd.getAttachedPhotos()).containsExactly(photos.get(1), photos.get(0));

    }


}