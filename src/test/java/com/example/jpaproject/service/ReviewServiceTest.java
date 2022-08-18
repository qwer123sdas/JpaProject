package com.example.jpaproject.service;
import com.example.jpaproject.domain.*;
import com.example.jpaproject.dto.ReviewDto;
import com.example.jpaproject.repository.MileageRepository;
import com.example.jpaproject.repository.PhotoRepository;
import com.example.jpaproject.repository.ReviewRepository;
import com.example.jpaproject.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    UserRepository userRepository;

    @Test
    @DisplayName("글자 1, 사진 2, 첫 리뷰x :: 총점 2")
    public void 테스트_저장1() throws Exception{
        //given
        UUID userId = UUID.randomUUID();
        userRepository.save(Users.builder().id(userId).build());

        List<Photo> photos = new ArrayList<>();
        photos.add(Photo.builder().attachedPhotoId(UUID.randomUUID()).build());
        photos.add(Photo.builder().attachedPhotoId(UUID.randomUUID()).build());
        photoRepository.saveAll(photos);

        List<UUID> attachedPhotoIds = photos.stream().map(Photo::getId).collect(Collectors.toList());
        ReviewDto reviewDto = ReviewDto.builder()
                            .reviewId(UUID.randomUUID())
                            .userId(userId)
                            .attachedPhotoIds(attachedPhotoIds)
                            .content("좋아요!")
                            .build();

        //when
        reviewService.saveReview(reviewDto);

        Users user = userRepository.findOne(userId); //DataIntegrityViolationException

        //then
        List<Mileage> mileageList = mileageRepository.getAllMileageByUserId(user.getId());
        int result = mileageList.stream()
                        .mapToInt(Mileage::getPoint)
                                .sum();
        Assertions.assertThat(result).isEqualTo(2);
    }


}