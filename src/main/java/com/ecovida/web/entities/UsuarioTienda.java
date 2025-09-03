package com.ecovida.web.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Builder
@Getter
@Entity
@Table(name = "usuario_tienda")
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioTienda {
    @Id
    @Column(name = "id_usuario_tienda", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "usuarios_id", nullable = false)
    private Usuario usuarios;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "tienda_id_tienda", nullable = false)
    private Tienda tiendaIdTienda;

}