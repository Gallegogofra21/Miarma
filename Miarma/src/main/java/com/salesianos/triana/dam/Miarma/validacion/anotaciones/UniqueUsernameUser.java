package com.salesianos.triana.dam.Miarma.validacion.anotaciones;

import com.salesianos.triana.dam.Miarma.validacion.validadores.UniqueUsernameValidatorUsuario;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueUsernameValidatorUsuario.class)
@Documented
public @interface UniqueUsernameUser {
    String message() default "El username del usuario debe ser único";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
