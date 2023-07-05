package com.grupo12.multievents.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "avatars")
public class Avatar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "url")
    private String url;

    @JsonIgnore
    @OneToMany(mappedBy = "avatar", fetch = FetchType.LAZY)
    private List<User> user;

    public Avatar(String url) {
        this.url = url;
    }
}
