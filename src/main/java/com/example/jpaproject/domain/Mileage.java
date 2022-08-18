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
    @Column(name="mileage_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(columnDefinition = "BINARY(16)")
    private UUID userId;

    private int point;

    private String reason;

    @Enumerated(EnumType.STRING)
    private MileageStatus status; // ADD, DELETE, MODIFY

/*
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
*/

    // == 생성 메서드 == //
    /*
    * 빌더와 메소드를 통해 구현
    * */
    public Mileage() {}
    @Builder
    private Mileage(UUID userId, int point, String reason){
        this.userId = userId;
        this.point = point;
        this.reason = reason;
    }
    public static Mileage addMileage(UUID userId, int point, String reason){
        Mileage mileage = new Mileage(userId, point, reason);
        mileage.id = UUID.randomUUID();
        return mileage;
    }

    // == 비지니스 로직 == //
    /*
    * 포인트 추가
    * */
    public void addPoint(int point, String reason){
        this.point += point;
        this.reason += reason;
    }

    /*
     * 포인트 감소
     * */
    public void removePoint(int point, String reason){
        int restPoint = this.point - point;
        if(restPoint < 0) {
            // 예외 발생
        }
        this.point -= restPoint;
        this.reason += reason;
    }

    /*
    * 포인트 적립 이유
    * */
    public void addReason(String reason){
        this.reason = reason;
    }

    /*
     * 마일리지 총액 계산
     * */
 /*   public int getTotalMileage(){
        int totalMileage = this.stream()
                .mapToInt(Mileage::getPoint)
                .sum();
        return totalMileage;
    }*/



    // == 조회 로직 == //



}
