package com.example.jpaproject.domain;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = "공백만 입력할 수 없습니다.")
    private String content;
    private int point;

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

    // == 비지니스 로직 == //
    public void update(String content, List<Photo> attachedPhotos,  ReviewStatus status){
        this.content = content;
        this.attachedPhotos = attachedPhotos;
        this.status = status;
    }

    public Review remove(Place place, String content, ReviewStatus status){
        Review review = Review.builder()
                .place(place)
                .content(content)
                .status(status)
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

    // == 포인트 로직 == //
    public void setPoint(int point){
        this.point = point;
    }


}
