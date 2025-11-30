package com.mateo.spotify.song.entity;

import com.mateo.spotify.album.entity.AlbumEntity;
import com.mateo.spotify.playlist.entity.PlayListEntity;
import com.mateo.spotify.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "songs")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SongEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private double duration;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false, name = "image_url")
    private String imageUrl;
    @Column(nullable = false, name = "song_url")
    private String songUrl;


    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserEntity author;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private AlbumEntity album;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private GenreEntity genre;

    @ManyToMany(mappedBy = "songs")
    private List<PlayListEntity> playLists;




}
