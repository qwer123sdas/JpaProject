package com.example.jpaproject.domain;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Getter
public class Review {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name="review_id", columnDefinition = "BINARY(16)")
    private UUID id;

/*    @Column(columnDefinition = "BINARY(16)")
    private UUID userId;

    @Column(columnDefinition = "BINARY(16)")
    private UUID placeId;*/

    private String content;

    @Enumerated(EnumType.STRING)
    private ReviewStatus status;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;


    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Photo> attachedPhotos = new ArrayList<>();

    @Column(columnDefinition = "BOOLEAN")
    private boolean first;


    // == 생성 메서드 == //
    /*
     * 빌더와 메소드를 통해 구현
     * */
    public Review() {}
    @Builder
    private Review(Users users, Place place, List<Photo> attachedPhotos, String content, ReviewStatus status, boolean first){
        this.users = users;
        this.place = place;
        this.attachedPhotos = attachedPhotos;
        this.content = content;
        this.status = status;
        this.first = first;
    }

    /*public static Review addReview(UUID userId, UUID placeId, String content){
        Review review = new Review(userId, placeId,content);
        //review.id = UUID.randomUUID();
        review.status = ReviewStatus.ADD;
        return review;
    }*/

    // == 비지니스 로직 == //
    public Review update(Users users, Place place, String content){
        Review review = Review.builder()
                .users(users)
                .place(place)
                .content(content)
                .build();
        return review;
    }

    public Review remove(Users users, Place place, String content){
        Review review = Review.builder()
                .users(users)
                .place(place)
                .content(content)
                .build();
        return review;
    }

    // == 사진 로직 == //
    public void addAttachPhotos(List<UUID> photoIds){
        List<Photo> reviewPhotos = new ArrayList<>();
        for(UUID id : photoIds){
            Photo photo = Photo.builder()
                    .attachedPhotoId(id)
                    .review(this)
                    .build();
            attachedPhotos.add(photo);
        }

        this.attachedPhotos.addAll(reviewPhotos);
    }


}
