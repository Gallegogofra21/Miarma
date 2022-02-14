package com.salesianos.triana.dam.Miarma.users.dto;

import com.salesianos.triana.dam.Miarma.users.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {

    public CreateUserDto convertUsuarioToNewUser(Usuario p) {
        return CreateUserDto.builder()
                .username(p.getUsername())
                .email(p.getEmail())
                .avatar(p.getAvatar())
                .password(p.getPassword())
                .password2(p.getPassword())
                .nombre(p.getNombre())
                .build();
    }

}
