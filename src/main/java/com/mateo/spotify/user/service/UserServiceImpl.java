package com.mateo.spotify.user.service;

import com.mateo.spotify.user.dto.UserResponseDTO;
import com.mateo.spotify.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public UserResponseDTO uploadProfilePicture(MultipartFile image) {

        if (image == null || image.isEmpty()) throw new RuntimeException("El archivo no existe");

        String filename = image.getOriginalFilename();
        if (filename == null) throw new RuntimeException("El archivo no existe");

        String extension = filename.substring(filename.lastIndexOf('.') + 1).toLowerCase();

        List<String> allowedExtensions = List.of("jpg","jpeg","png");
        if (!allowedExtensions.contains(extension)) throw new RuntimeException("Extension no valida");


        return null;
    }
}
