package com.example.jpaproject.domain;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
public class Place {
    @Id
    @Column(name="place_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<Review> reviews;

    public Place() {

    }
    @Builder
    private Place(UUID id){
        this.id = id;
    }


}
