package com.salesianos.triana.dam.Miarma.errores.exception;

import javax.persistence.EntityNotFoundException;

public class ListEntityNotFoundException extends EntityNotFoundException {

    public ListEntityNotFoundException(Class clazz) {
        super(String.format("No se pueden encontrar elementos del tipo %s ", clazz.getName()));
    }
}
