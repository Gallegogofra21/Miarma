package com.salesianos.triana.dam.Miarma.users.dto;

import com.salesianos.triana.dam.Miarma.users.model.Perfil;
import com.salesianos.triana.dam.Miarma.validacion.anotaciones.UniqueEmailUser;
import com.salesianos.triana.dam.Miarma.validacion.anotaciones.UniqueUsernameUser;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserDto {

    @NotBlank(message = "{user.username.blank}")
    @UniqueUsernameUser(message = "{user.username.unique}")
    private String username;
    @NotBlank(message = "{user.name.blank}")
    private String nombre;
    @NotBlank(message = "{user.email.blank}")
    @UniqueEmailUser(message = "{user.email.unique}")
    private String email;
    private LocalDate fechaNacimiento;
    private String password;
    private String password2;
    private String avatar;
    private Perfil perfil;
}
