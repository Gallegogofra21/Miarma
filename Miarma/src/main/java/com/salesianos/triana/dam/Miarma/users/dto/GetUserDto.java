package com.salesianos.triana.dam.Miarma.users.dto;

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
}
