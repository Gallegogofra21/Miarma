package com.salesianos.triana.dam.Miarma.users.dto;

import lombok.*;

import java.time.LocalDate;
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
    private LocalDate fechaNacimiento;
    private String password;
    private String password2;
    private String avatar;
}
