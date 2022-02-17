package com.salesianos.triana.dam.Miarma.dto;

import com.salesianos.triana.dam.Miarma.model.Post;
import org.springframework.stereotype.Component;

@Component
public class ConverterPostDto {
    public GetPostDto getPostToDto(Post p) {
        return GetPostDto.builder()
                .id(p.getId())
                .titulo(p.getTitulo())
                .texto(p.getTexto())
                .file(p.getFile())
                .usuario(p.getUsuario().getUsername())
                .publica(p.getPublica())
                .build();
    }
}
