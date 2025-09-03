package com.ecovida.web.service.impl;

import com.ecovida.web.entities.ComentarioTienda;
import com.ecovida.web.entities.Tienda;
import com.ecovida.web.entities.Usuario;
import com.ecovida.web.repository.ComentarioTiendaRepository;
import com.ecovida.web.repository.TiendaRepository;
import com.ecovida.web.repository.UsuarioRepository;
import com.ecovida.web.service.ComentarioTiendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ComentarioTiendaServiceImpl implements ComentarioTiendaService {

    private final TiendaRepository tiendaRepository;
    private final UsuarioRepository usuarioRepository;
    private final ComentarioTiendaRepository comentarioTiendaRepository;

    @Override
    public Page<ComentarioTienda> obtenerComentariosPorTienda(Long idTienda, int pagina, int tamanio) {
        Pageable pageable = PageRequest.of(pagina, tamanio, Sort.by("fechaCreacion").descending());
        return comentarioTiendaRepository.findByTiendaId(idTienda, pageable);
    }

    @Override
    public ComentarioTienda guardarComentario(Long tiendaId, Long usuarioId, String contenido) throws Exception {
        Tienda tienda = tiendaRepository.findById(tiendaId)
                .orElseThrow(() -> new Exception("Tienda no encontrada"));
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new Exception("Usuario no encontrado"));
        ComentarioTienda nuevoComentario = new ComentarioTienda();
        nuevoComentario.setTienda(tienda);
        nuevoComentario.setUsuario(usuario);
        nuevoComentario.setContenido(contenido);
        nuevoComentario.setFechaCreacion(LocalDateTime.now());
        return comentarioTiendaRepository.save(nuevoComentario);
    }

    @Override
    public void save(ComentarioTienda comentarioTienda) {
        comentarioTiendaRepository.save(comentarioTienda);
    }

    @Override
    public void eliminarComentario(Long comentarioId) {
        comentarioTiendaRepository.deleteById(comentarioId);
    }

    @Override
    public ComentarioTienda obtenerComentarioPorId(Long idComentario) throws Exception {
        return comentarioTiendaRepository.findById(idComentario)
                .orElseThrow(() -> new Exception("Comentario no encontrado"));
    }
}
