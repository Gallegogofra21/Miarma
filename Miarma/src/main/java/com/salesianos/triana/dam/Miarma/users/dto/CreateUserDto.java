package com.salesianos.triana.dam.Miarma.users.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserDto {

    private String username;
    private String nombre;
    private String email;
    private Date fechaNacimiento;
    private String password;
    private String password2;
    private String avatar;
}
