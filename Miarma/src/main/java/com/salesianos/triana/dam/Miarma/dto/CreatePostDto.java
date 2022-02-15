package com.salesianos.triana.dam.Miarma.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreatePostDto {

    private String titulo;

    private String texto;

    private boolean publica;

    private String file;
}
