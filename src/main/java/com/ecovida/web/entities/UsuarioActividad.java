package com.ecovida.web.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Builder
@Getter
@Entity
@Table(name = "usuario_actividad")
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioActividad {
    @Id
    @Column(name = "id_usuario_actividad", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "usuarios_id", nullable = false)
    private Usuario usuarios;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "actividad_id_actividad", nullable = false)
    private Actividad actividadIdActividad;

}