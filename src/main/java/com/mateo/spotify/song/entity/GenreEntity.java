package com.mateo.spotify.song.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "genres")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "genre")
    public List<SongEntity> songs;
}
