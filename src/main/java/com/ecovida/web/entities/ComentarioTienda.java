package com.ecovida.web.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Builder
@Getter
@Entity
@Table(name = "comentario_tienda")
@NoArgsConstructor
@AllArgsConstructor
public class ComentarioTienda {

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
    @JoinColumn(name = "tienda_id", nullable = false)
    private Tienda tienda;
}
