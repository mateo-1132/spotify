package com.mateo.spotify.user.service;

import com.mateo.spotify.user.dto.UserResponseDTO;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    UserResponseDTO uploadProfilePicture(MultipartFile image);
    UserResponseDTO getCurrentUser();
}
