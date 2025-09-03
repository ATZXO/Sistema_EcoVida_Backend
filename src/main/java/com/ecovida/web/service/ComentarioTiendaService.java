package com.ecovida.web.service;

import com.ecovida.web.entities.ComentarioTienda;
import org.springframework.data.domain.Page;

public interface ComentarioTiendaService {
    Page<ComentarioTienda> obtenerComentariosPorTienda(Long idTienda, int pagina, int tamanio);
    ComentarioTienda guardarComentario(Long tiendaId, Long usuarioId, String contenido) throws Exception;
    void save(ComentarioTienda comentarioTienda);
    void eliminarComentario(Long comentarioId) throws Exception;
    ComentarioTienda obtenerComentarioPorId(Long idComentario) throws Exception;
}
