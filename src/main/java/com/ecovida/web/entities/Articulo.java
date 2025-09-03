package com.ecovida.web.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Builder
@Getter
@Entity
@Table(name = "articulo")
@NoArgsConstructor
@AllArgsConstructor
public class Articulo {
    @Id
    @Column(name = "id_articulo", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name = "titulo")
    private String titulo;

    @Size(max = 255)
    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Size(max = 255)
    @Column(name = "autor")
    private String autor;

    @Size(max = 255)
    @Column(name = "estado")
    private String estado;

    @Size(max = 255)
    @Column(name = "ruta_pdf")
    private String rutaPdf;

    @OneToMany(mappedBy = "articuloIdArticulo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<UsuarioArticulo> usuarioArticulos = new ArrayList<>();

    @OneToMany(mappedBy = "articulo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ComentarioArticulo> comentarios = new ArrayList<>();
}