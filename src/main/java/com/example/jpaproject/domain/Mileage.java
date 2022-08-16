package com.example.jpaproject.domain;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
public class Mileage {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name="mileage_id", columnDefinition = "BINARY(16)")
    private UUID id;

    private int point;
    private LocalDateTime inserted;
    private String comment;

    @Enumerated(EnumType.STRING)
    private MileageStatus status; // ADD, DELETE, MODIFY

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    // == 생성 메서드 == //
    public Mileage() {}
    @Builder
    private Mileage(int point, String comment,MileageStatus status){
        this.point = point;
        this.comment = comment;
        this.status = status;
    }
    public static Mileage createMileage(int point, String comment){
        Mileage mileage = Mileage.builder()
                .point(point)
                .comment(comment)
                .build();
        return mileage;
    }

    // == 비지니스 로직 == //
    /*
    * 포인트 추가
    * */
    public void addPoint(int point){
        this.point += point;
    }

    /*
     * 포인트 감소
     * */
    public void removePoint(int point){
        int restPoint = this.point - point;
        if(restPoint < 0) {
            // 예외 발생
        }
        this.point = restPoint;
    }

    /*
    * 정보 수정
    * */
    public void change(String comment){
        this.comment = comment;
        this.status = MileageStatus.MODIFY;
    }

    // == 조회 로직 == //



}
