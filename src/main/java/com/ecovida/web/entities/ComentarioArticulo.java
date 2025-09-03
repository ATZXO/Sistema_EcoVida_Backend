package com.ecovida.web.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Builder
@Getter
@Entity
@Table(name = "comentario_articulo")
@NoArgsConstructor
@AllArgsConstructor
public class ComentarioArticulo {
    @Id
    @Column(name = "id_comentario", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 1000)
    @Column(name = "contenido", nullable = false)
    private String contenido;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "usuarios_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "articulo_id", nullable = false)
    private Articulo articulo;
}
