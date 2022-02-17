package com.salesianos.triana.dam.Miarma.validacion.validadores;

import com.salesianos.triana.dam.Miarma.users.service.impl.UserEntityService;
import com.salesianos.triana.dam.Miarma.validacion.anotaciones.UniqueEmailUser;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UniqueEmailValidatorUsuario implements ConstraintValidator<UniqueEmailUser, String> {
    private final UserEntityService service;

    @Override
    public void initialize(UniqueEmailUser constraintAnnotation) {

    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return StringUtils.hasText(email) && !service.comprobarEmail(email);
    }
}
