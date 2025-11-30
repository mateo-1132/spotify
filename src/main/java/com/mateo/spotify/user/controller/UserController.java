package com.mateo.spotify.user.controller;

import com.mateo.spotify.user.dto.UserResponseDTO;
import com.mateo.spotify.user.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/profile-picture")
    public ResponseEntity<@NonNull UserResponseDTO> uploadProfilePicture(@RequestParam("file")MultipartFile file){
        return ResponseEntity.status(HttpStatus.OK).body(userService.uploadProfilePicture(file));
    }

    @GetMapping("/me")
    public ResponseEntity<@NonNull UserResponseDTO> getCurrentUser(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getCurrentUser());
    }


}
