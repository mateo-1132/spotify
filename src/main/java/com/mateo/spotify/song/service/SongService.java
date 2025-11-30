package com.mateo.spotify.song.service;

import com.mateo.spotify.song.dto.CreateSongRequestDTO;
import com.mateo.spotify.song.dto.SongResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface SongService {
    SongResponseDTO getSongById(UUID id);
    SongResponseDTO createSong(CreateSongRequestDTO createSongRequestDTO, MultipartFile song, MultipartFile image);
}
