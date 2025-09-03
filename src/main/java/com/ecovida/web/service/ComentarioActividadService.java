package com.ecovida.web.service;

import com.ecovida.web.entities.ComentarioActividad;
import org.springframework.data.domain.Page;

public interface ComentarioActividadService {
    Page<ComentarioActividad> obtenerComentariosPorActividad(Long idActividad, int pagina, int tamanio);
    ComentarioActividad guardarComentario(Long comentarioId, Long usuarioId, String contenido) throws Exception;
    void save(ComentarioActividad comentarioActividad);
    void eliminarComentario(Long comentarioId) throws Exception;
    ComentarioActividad obtenerComentarioPorId(Long idComentario) throws Exception;
}
