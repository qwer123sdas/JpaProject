package com.example.jpaproject.domain;

import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
