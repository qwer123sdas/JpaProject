package com.example.jpaproject.domain;

import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
public class User {
    @Id @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "user_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Mileage> mileages = new ArrayList<>();


    // == 비지니스 로직 == //
    /*
     * 마일리지 총액 계산
     * */
    public int getTotalMileage(){
        int totalMileage = mileages.stream()
                                .mapToInt(Mileage::getPoint)
                                .sum();
        return totalMileage;
    }
}
