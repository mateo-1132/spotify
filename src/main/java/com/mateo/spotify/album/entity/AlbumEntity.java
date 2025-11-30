package com.mateo.spotify.album.entity;

import com.mateo.spotify.song.entity.SongEntity;
import com.mateo.spotify.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "albums")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AlbumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false, name = "image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "album")
    private List<SongEntity> songs;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserEntity author;




}
