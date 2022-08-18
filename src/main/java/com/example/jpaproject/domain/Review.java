package com.example.jpaproject.domain;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
public class Review {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name="review_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(columnDefinition = "BINARY(16)")
    private UUID userId;

    @Column(columnDefinition = "BINARY(16)")
    private UUID placeId;

    private String content;

    @Enumerated(EnumType.STRING)
    private ReviewStatus status;

/*
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;
*/

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private final List<Photo> attachedPhotos = new ArrayList<>();


    // == 생성 메서드 == //
    /*
     * 빌더와 메소드를 통해 구현
     * */
    public Review() {}
    @Builder
    private Review(UUID userId, UUID placeId, String content){
        this.userId = userId;
        this.placeId = placeId;
        this.content = content;
    }

    public static Review addReview(UUID userId, UUID placeId, String content){
        Review review = new Review(userId, placeId,content);
        //review.id = UUID.randomUUID();
        review.status = ReviewStatus.ADD;
        return review;
    }

    // == 비지니스 로직 == //
    public Review update(UUID userId, UUID placeId, String content){
        Review review = new Review(userId, placeId,content);
        return review;
    }

    public Review remove(UUID userId, UUID placeId, String content){
        Review review = new Review(userId, placeId,content);
        return review;
    }

    // == 사진 로직 == //
    public void addPhoto(Photo photo){
        attachedPhotos.add(photo);
        photo.addReview(this);
    }
    public void removePhoto(Photo photo){
        attachedPhotos.remove(photo);
        photo.removeReview(null);
    }



}
