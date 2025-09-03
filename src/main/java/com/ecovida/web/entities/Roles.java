package com.ecovida.web.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Builder
@Getter
@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
public class Roles {
    @Id
    @Column(name = "rol_id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "nombre")
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "rolesRol")
    private Set<UsuarioRol> usuarioRoles = new HashSet<>();

}