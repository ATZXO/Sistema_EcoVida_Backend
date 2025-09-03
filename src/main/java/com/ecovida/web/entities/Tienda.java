package com.ecovida.web.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Builder
@Getter
@Entity
@Table(name = "tienda")
@NoArgsConstructor
@AllArgsConstructor
public class Tienda {
    @Id
    @Column(name = "id_tienda", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name = "nombre")
    private String nombre;

    @Size(max = 255)
    @Column(name = "descripcion")
    private String descripcion;

    @Size(max = 255)
    @Column(name = "lugar")
    private String lugar;

    @OneToMany(mappedBy = "tiendaIdTienda", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<UsuarioTienda> usuarioTienda = new ArrayList<>();

    @OneToMany(mappedBy = "tienda", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ComentarioTienda> comentarios = new ArrayList<>();
}