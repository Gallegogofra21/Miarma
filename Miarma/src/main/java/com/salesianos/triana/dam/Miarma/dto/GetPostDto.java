package com.salesianos.triana.dam.Miarma.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetPostDto {

    private Long id;
    private String titulo;
    private String texto;
    private String file;
    private String usuario;
}
