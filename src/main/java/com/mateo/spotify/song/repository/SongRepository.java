package com.mateo.spotify.song.repository;

import com.mateo.spotify.song.entity.SongEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SongRepository extends JpaRepository<@NonNull SongEntity, @NonNull UUID> {
    Optional<SongEntity> findById(UUID id);
}
