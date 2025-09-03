package com.ecovida.web.entities;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class UsuarioDetallesDTO {
    private String nombre;
    private String apellido;
    private String telefono;
}
