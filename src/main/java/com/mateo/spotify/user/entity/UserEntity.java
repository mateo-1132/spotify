package com.mateo.spotify.user.entity;

import com.mateo.spotify.album.entity.AlbumEntity;
import com.mateo.spotify.playlist.entity.PlayListEntity;
import com.mateo.spotify.song.entity.SongEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(name = "profile_picture_url")
    private String profilePictureUrl;

    @OneToMany(mappedBy = "author")
    private List<SongEntity> songs;

    @OneToMany(mappedBy = "author")
    private List<AlbumEntity> albums;

    @ManyToMany
    @JoinTable(
            name = "user_follows",
            joinColumns = @JoinColumn(name = "follower_id"),
            inverseJoinColumns = @JoinColumn(name = "followed_id")

    )
    private List<UserEntity> following;

    @ManyToMany(mappedBy = "following")
    private List<UserEntity> followers;

    @OneToMany(mappedBy = "creator")
    private List<PlayListEntity> playLists;


}
