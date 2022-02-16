package com.salesianos.triana.dam.Miarma.users.controller;

import com.salesianos.triana.dam.Miarma.users.dto.CreateUserDto;
import com.salesianos.triana.dam.Miarma.users.dto.GetUserDto;
import com.salesianos.triana.dam.Miarma.users.dto.UserDtoConverter;
import com.salesianos.triana.dam.Miarma.users.model.Usuario;
import com.salesianos.triana.dam.Miarma.users.repo.UserEntityRepository;
import com.salesianos.triana.dam.Miarma.users.service.impl.UserEntityService;
import com.salesianos.triana.dam.Miarma.util.PaginationLinksUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserEntityService userEntityService;
    private final UserEntityRepository userEntityRepository;
    private final UserDtoConverter userDtoConverter;
    private final PaginationLinksUtil paginationLinksUtil;

    @PostMapping("/auth/register")
    public ResponseEntity<GetUserDto> nuevoUser (@RequestPart("file") MultipartFile file, @RequestPart("user") CreateUserDto newUser) {
        Usuario saved = userEntityService.saveUser(newUser, file);

        if(saved == null)
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.ok(userDtoConverter.convertUsuarioToNewUser(saved));
    }

    @PutMapping("/profile/me")
    public ResponseEntity<GetUserDto> editPost (@RequestPart("file") MultipartFile file, @RequestPart("user") CreateUserDto newUser, @AuthenticationPrincipal Usuario currentUser) throws IOException {
        Usuario saved = userEntityService.edit(newUser, file, currentUser);

        if(saved == null)
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.ok(userDtoConverter.convertUsuarioToNewUser(saved));
    }
}
