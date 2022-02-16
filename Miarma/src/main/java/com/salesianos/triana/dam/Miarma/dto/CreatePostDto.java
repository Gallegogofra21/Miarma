package com.salesianos.triana.dam.Miarma.dto;

import com.salesianos.triana.dam.Miarma.model.Public;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreatePostDto {

    private String titulo;

    private String texto;

    private Public aPublic;

    private String file;

    private String reescalada;

    private Long userId;
}
