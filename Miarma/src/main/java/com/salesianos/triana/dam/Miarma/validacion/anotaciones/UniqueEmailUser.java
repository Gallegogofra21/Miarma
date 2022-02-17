package com.salesianos.triana.dam.Miarma.validacion.anotaciones;

import com.salesianos.triana.dam.Miarma.validacion.validadores.UniqueEmailValidatorUsuario;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueEmailValidatorUsuario.class)
@Documented
public @interface UniqueEmailUser {
    String message() default "El email del usuario debe ser único";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
