package com.ecovida.web.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Builder
@Getter
@Entity
@Table(name = "usuario_rol")
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRol {
    @Id
    @Column(name = "usuario_rol_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "usuarios_id", nullable = false)
    private Usuario usuarios;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "roles_rol_id", nullable = false)
    private Roles rolesRol;

}