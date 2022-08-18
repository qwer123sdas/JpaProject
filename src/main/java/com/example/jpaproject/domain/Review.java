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
    @Id @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name="review_id", columnDefinition = "BINARY(16)")
    private UUID id;

    private String content;
    private String action;
    private LocalDateTime inserted;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;

    @ManyToOne
    @JoinColumn(name = "attached_photo_id")
    private Photo photo;


    // == 생성 메서드 == //
    /*
     * 빌더와 메소드를 통해 구현
     * */
    public Review() {}
    @Builder
    private Review(String content, String action){
        this.content = content;
        this.action = action;
    }
    public static Review createReview(String content, String action){
        Review review = Review.builder()
                .content(content)
                .action(action)
                .build();
        return review;
    }



}
