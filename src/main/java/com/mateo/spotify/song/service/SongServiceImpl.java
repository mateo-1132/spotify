package com.mateo.spotify.song.service;

import com.mateo.spotify.song.dto.CreateSongRequestDTO;
import com.mateo.spotify.song.dto.SongResponseDTO;
import com.mateo.spotify.song.entity.SongEntity;
import com.mateo.spotify.song.mapper.SongMapper;
import com.mateo.spotify.song.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService{

    private final SongRepository songRepository;
    private final SongMapper songMapper;


    @Override
    public SongResponseDTO getSongById(UUID id) {

        SongEntity songEntity = songRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe la cancion"));
        return songMapper.toDTO(songEntity);

    }

    @Override
    public SongResponseDTO createSong(CreateSongRequestDTO createSongRequestDTO, MultipartFile song, MultipartFile image) {
        return null;
    }
}
