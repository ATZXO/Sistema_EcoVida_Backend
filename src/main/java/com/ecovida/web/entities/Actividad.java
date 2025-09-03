package com.ecovida.web.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Entity
@Builder
@Getter
@Table(name = "actividad")
@NoArgsConstructor
@AllArgsConstructor
public class Actividad {
    @Id
    @Column(name = "id_actividad", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name = "titulo")
    private String titulo;

    @Size(max = 255)
    @Column(name = "descripcion")
    private String descripcion;

    @Size(max = 255)
    @Column(name = "distrito")
    private String distrito;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Size(max = 255)
    @Column(name = "estado")
    private String estado;

    @OneToMany(mappedBy = "actividadIdActividad", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<UsuarioActividad> usuarioActividad = new ArrayList<>();

    @OneToMany(mappedBy = "actividad", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ComentarioActividad> comentarios = new ArrayList<>();
}