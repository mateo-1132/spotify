package com.mateo.spotify.user.service;

import com.mateo.spotify.user.dto.UserResponseDTO;
import com.mateo.spotify.user.entity.UserEntity;
import com.mateo.spotify.user.mapper.UserMapper;
import com.mateo.spotify.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Value("${spring.application.images.path}")
    private String imagesPath;


    @Override
    public UserResponseDTO uploadProfilePicture(MultipartFile image) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getName() == null) {
            throw new RuntimeException("El usuario no esta autenticado!!");
        }

        String username = authentication.getName();

        if (image == null || image.isEmpty()) throw new RuntimeException("El archivo no existe");

        String filename = image.getOriginalFilename();
        if (filename == null) throw new RuntimeException("El archivo no existe");

        String extension = filename.substring(filename.lastIndexOf('.') + 1).toLowerCase();

        List<String> allowedExtensions = List.of("jpg","jpeg","png");
        if (!allowedExtensions.contains(extension)) throw new RuntimeException("Extension no valida");


        try {

            Path uploadPath = Paths.get(imagesPath);
            if (!Files.exists(uploadPath)){
                Files.createDirectories(uploadPath);
            }

            String newFilename = UUID.randomUUID() + "." + extension;

            Path filePath = uploadPath.resolve(newFilename);

            Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            UserEntity user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            user.setProfilePictureUrl(imagesPath + newFilename);

            return userMapper.toDTO(userRepository.save(user));




        }catch (IOException ex){
            throw new RuntimeException("Error al guardar la images " + ex);
        }

    }
}
