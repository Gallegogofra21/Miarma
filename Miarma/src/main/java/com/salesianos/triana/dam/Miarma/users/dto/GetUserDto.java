package com.salesianos.triana.dam.Miarma.users.dto;

import com.salesianos.triana.dam.Miarma.users.model.Perfil;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetUserDto {

    private String username;
    private String nombre;
    private String email;
    private String avatar;
    private Perfil perfil;
}
