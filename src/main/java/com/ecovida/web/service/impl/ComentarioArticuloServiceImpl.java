package com.ecovida.web.service.impl;

import com.ecovida.web.entities.Articulo;
import com.ecovida.web.entities.ComentarioArticulo;
import com.ecovida.web.entities.Usuario;
import com.ecovida.web.repository.ArticuloRepository;
import com.ecovida.web.repository.ComentarioArticuloRepository;
import com.ecovida.web.repository.UsuarioRepository;
import com.ecovida.web.service.ComentarioArticuloService;
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
public class ComentarioArticuloServiceImpl implements ComentarioArticuloService {

    private final UsuarioRepository usuarioRepository;
    private final ArticuloRepository articuloRepository;
    private final ComentarioArticuloRepository comentarioArticuloRepository;

    @Override
    public Page<ComentarioArticulo> obtenerComentariosPorArticulo(Long idArticulo, int pagina, int tamanio) {
        Pageable pageable = PageRequest.of(pagina, tamanio, Sort.by("fechaCreacion").descending());
        return comentarioArticuloRepository.findByArticuloId(idArticulo, pageable);
    }

    @Override
    public ComentarioArticulo guardarComentario(Long articuloId, Long usuarioId, String contenido) throws Exception {
        Articulo articulo = articuloRepository.findById(articuloId)
                .orElseThrow(() -> new Exception("Articulo no encontrado"));
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new Exception("Usuario no encontrado"));

        ComentarioArticulo nuevoComentario = new ComentarioArticulo();
        nuevoComentario.setArticulo(articulo);
        nuevoComentario.setUsuario(usuario);
        nuevoComentario.setContenido(contenido);
        nuevoComentario.setFechaCreacion(LocalDateTime.now());

        return comentarioArticuloRepository.save(nuevoComentario);
    }

    @Override
    public void save(ComentarioArticulo comentarioArticulo) {
        comentarioArticuloRepository.save(comentarioArticulo);
    }

    @Override
    public void eliminarComentario(Long comentarioId){
        comentarioArticuloRepository.deleteById(comentarioId);
    }

    @Override
    public ComentarioArticulo obtenerComentarioPorId(Long idComentario) throws Exception {
        return comentarioArticuloRepository.findById(idComentario)
                .orElseThrow(() -> new Exception("Comentario no encontrado"));
    }
}
