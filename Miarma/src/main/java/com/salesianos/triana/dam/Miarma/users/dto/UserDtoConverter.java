package com.salesianos.triana.dam.Miarma.users.dto;

import com.salesianos.triana.dam.Miarma.users.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {

    public GetUserDto convertUsuarioToNewUser(Usuario p) {
        return GetUserDto.builder()
                .username(p.getUsername())
                .nombre(p.getNombre())
                .email(p.getEmail())
                .avatar(p.getAvatar())
                .perfil(p.getPerfil())
                .build();
    }

}
