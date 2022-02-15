package com.salesianos.triana.dam.Miarma.users.controller;

import com.salesianos.triana.dam.Miarma.users.dto.CreateUserDto;
import com.salesianos.triana.dam.Miarma.users.dto.GetUserDto;
import com.salesianos.triana.dam.Miarma.users.dto.UserDtoConverter;
import com.salesianos.triana.dam.Miarma.users.model.Usuario;
import com.salesianos.triana.dam.Miarma.users.repo.UserEntityRepository;
import com.salesianos.triana.dam.Miarma.users.service.impl.UserEntityService;
import com.salesianos.triana.dam.Miarma.util.PaginationLinksUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
}
