package com.example.jpaproject.domain;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
public class Users {
    @Id
    @Column(name = "user_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Review> reviews;

    public Users() {

    }
    @Builder
    private Users(UUID id){
        this.id = id;
    }
}
