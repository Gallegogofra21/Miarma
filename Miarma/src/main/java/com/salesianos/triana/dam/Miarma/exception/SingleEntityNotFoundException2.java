package com.salesianos.triana.dam.Miarma.exception;

import javax.persistence.EntityNotFoundException;

public class SingleEntityNotFoundException2 extends EntityNotFoundException {
    public SingleEntityNotFoundException2(String email, Class clazz) {
        super(String.format("No se puede una entidad del tipo %s con el email: %s", clazz.getName()));
    }
}
