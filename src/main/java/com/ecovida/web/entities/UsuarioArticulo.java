package com.ecovida.web.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Builder
@Getter
@Entity
@Table(name = "usuario_articulo")
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioArticulo {
    @Id
    @Column(name = "id_usuario_articulo", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "usuarios_id", nullable = false)
    private Usuario usuarios;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "articulo_id_articulo", nullable = false)
    private Articulo articuloIdArticulo;
}