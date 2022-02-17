package com.salesianos.triana.dam.Miarma.dto;

import com.salesianos.triana.dam.Miarma.model.Public;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
@Builder
public class CreatePostDto {

    @NotBlank(message = "{post.titulo.blank}")
    private String titulo;

    private String texto;

    @NotBlank(message = "{post.aPublic.blank}")
    private Public aPublic;

    private String file;

    private String reescalada;

    private Long userId;
}
