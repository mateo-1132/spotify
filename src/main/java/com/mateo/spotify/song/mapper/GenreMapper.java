package com.mateo.spotify.song.mapper;

import com.mateo.spotify.song.dto.GenreResponseDTO;
import com.mateo.spotify.song.entity.GenreEntity;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper {

    public GenreResponseDTO toDTO(GenreEntity genreEntity){
        return GenreResponseDTO.builder()
                .id(genreEntity.getId())
                .name(genreEntity.getName())
                .build();
    }

}
