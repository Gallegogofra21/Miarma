package com.salesianos.triana.dam.Miarma.users.model;

import com.salesianos.triana.dam.Miarma.model.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue
    private Long id;

    private String nombre;
    private String apellidos;
    private String direccion;
    private String username;

    private LocalDate fechaNacimiento;

    private String email;
    private String telefono;
    private String avatar;
    private String password;
    private String password2;

    @Enumerated
    private Perfil perfil;

    @OneToMany
    private List<Post> posts;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
