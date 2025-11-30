package com.mateo.spotify.song.controller;

import com.mateo.spotify.song.dto.CreateSongRequestDTO;
import com.mateo.spotify.song.dto.SongResponseDTO;
import com.mateo.spotify.song.service.SongService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/songs")
@RequiredArgsConstructor
public class SongController {

    private final SongService songService;


    @GetMapping("/{id}")
    public ResponseEntity<@NonNull SongResponseDTO> getSongById(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(songService.getSongById(id));
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<@NonNull SongResponseDTO> createSong(
            @RequestPart("song") MultipartFile song,
            @RequestPart("image") MultipartFile image,
            @RequestPart("createSongRequestDTO") CreateSongRequestDTO createSongRequestDTO
            ){
        return ResponseEntity.status(HttpStatus.CREATED).body(songService.createSong(createSongRequestDTO,song,image));
    }



}
