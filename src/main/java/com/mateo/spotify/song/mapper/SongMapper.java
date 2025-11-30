package com.mateo.spotify.song.mapper;

import com.mateo.spotify.song.dto.SongResponseDTO;
import com.mateo.spotify.song.entity.SongEntity;
import com.mateo.spotify.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SongMapper {

    private final GenreMapper genreMapper;
    private final UserMapper userMapper;

    public SongResponseDTO toDTO(SongEntity songEntity){
        return SongResponseDTO.builder()
                .id(songEntity.getId())
                .title(songEntity.getTitle())
                .date(songEntity.getDate())
                .duration(songEntity.getDuration())
                .songUrl(songEntity.getSongUrl())
                .imageUrl(songEntity.getImageUrl())
                .genre(genreMapper.toDTO(songEntity.getGenre()))
                .author(userMapper.toDTO(songEntity.getAuthor()))
                .build();
    }

}
