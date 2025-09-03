package com.ecovida.web.service;

import com.ecovida.web.entities.ComentarioArticulo;
import org.springframework.data.domain.Page;


public interface ComentarioArticuloService {
    Page<ComentarioArticulo> obtenerComentariosPorArticulo(Long idArticulo, int pagina, int tamanio);
    ComentarioArticulo guardarComentario(Long articuloId, Long usuarioId, String contenido) throws Exception;
    void save(ComentarioArticulo comentarioArticulo);
    void eliminarComentario(Long comentarioId) throws Exception;
    ComentarioArticulo obtenerComentarioPorId(Long idComentario) throws Exception;
}
