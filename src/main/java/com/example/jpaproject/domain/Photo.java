package com.example.jpaproject.domain;

import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
public class Photo {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name="attached_photo_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @OneToMany(mappedBy = "photo")
    private List<Review> reviews = new ArrayList<>();
}
