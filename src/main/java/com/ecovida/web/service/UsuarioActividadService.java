package com.ecovida.web.service;

import com.ecovida.web.entities.Actividad;
import com.ecovida.web.entities.UsuarioActividad;

import java.util.List;

public interface UsuarioActividadService {
    List<Actividad> obtenerFavoritosPorUsuario(Long idUsuario);
    List<UsuarioActividad> obtenerAsistentesPorActividad(Long idActividad);
    UsuarioActividad guardarFavorito(Long idUsuario, Long idActividad) throws Exception;
    void eliminarFavorito(Long idUsuario, Long idActividad) throws Exception;
    boolean existeFavorito(Long idUsuario, Long idActividad);
}
