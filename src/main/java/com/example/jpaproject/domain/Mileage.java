package com.example.jpaproject.domain;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;


@Getter
@Entity
public class Mileage {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name="mileage_id", columnDefinition = "BINARY(16)")
    private UUID id;

/*    @Column(columnDefinition = "BINARY(16)")
    private UUID userId;*/

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

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
    private Mileage(Users users, int point, String reason){
        this.users = users;
        this.point = point;
        this.reason = reason;
    }
    public static Mileage addMileage(Users users, int point, String reason){
        Mileage mileage = new Mileage(users, point, reason);
        return mileage;
    }

    // == 비지니스 로직 == //


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
