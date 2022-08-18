package com.example.jpaproject.domain;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
public class Photo {
    @Id
    @Column(name="attached_photo_id", columnDefinition = "BINARY(16)")
    private UUID id;
                                                    // InvalidDataAccessApiUsageException: detached entity passed to persist:
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)  // java.lang.IllegalStateException: org.hibernate.TransientPropertyValueException:
    @JoinColumn(name = "review_id")
    private Review review;

    // == 생성 메서드 == //
    /*
     * 빌더와 메소드를 통해 구현
     * */
    public Photo() {

    }
    @Builder
    private Photo(UUID attachedPhotoId){
        this.id = attachedPhotoId;
    }

    // == 비지니스 로직 == //
    public Photo addPhoto(UUID attached_photo_id){
        Photo photo = Photo.builder()
                .attachedPhotoId(attached_photo_id)
                .build();
        return photo;
    }

    public void addReview(Review review){
        this.review = review;
    }

    public void removeReview(Review review) {
        this.review = review;
    }
}
