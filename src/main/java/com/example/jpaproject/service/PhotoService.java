package com.example.jpaproject.service;

import com.example.jpaproject.domain.Photo;
import com.example.jpaproject.domain.Review;
import com.example.jpaproject.repository.PhotoRepository;
import com.example.jpaproject.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PhotoService {
    private final PhotoRepository photoRepository;
    private final ReviewRepository reviewRepository;

    public void save(List<UUID> attachedPhotos, UUID reviewId) {
        for(UUID attachedPhoto : attachedPhotos){
            Review review = reviewRepository.findOne(reviewId);
            Photo photo = Photo.builder()
                                .attachedPhotoId(attachedPhoto)
                                .review(review)
                                .build();
            photoRepository.save(photo);
        }
    }
}
