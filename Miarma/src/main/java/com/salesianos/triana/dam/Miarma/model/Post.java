package com.salesianos.triana.dam.Miarma.model;

import com.salesianos.triana.dam.Miarma.users.model.Usuario;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String texto;

    private String file;

    private String reescalada;

    private Public publica;

    @ManyToOne
    @JoinColumn(name = "usuario_id", foreignKey = @ForeignKey(name = "FK_POST_USUARIO"))
    private Usuario usuario;
}
