package com.ecovida.web.service.impl;

import com.ecovida.web.entities.Actividad;
import com.ecovida.web.entities.ComentarioActividad;
import com.ecovida.web.entities.Usuario;
import com.ecovida.web.repository.ActividadRepository;
import com.ecovida.web.repository.ComentarioActividadRepository;
import com.ecovida.web.repository.UsuarioRepository;
import com.ecovida.web.service.ComentarioActividadService;
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
public class ComentarioActividadServiceImpl implements ComentarioActividadService {

    private final ActividadRepository actividadRepository;
    private final UsuarioRepository usuarioRepository;
    private final ComentarioActividadRepository comentarioActividadRepository;

    @Override
    public Page<ComentarioActividad> obtenerComentariosPorActividad(Long idActividad, int pagina, int tamanio) {
        Pageable pageable = PageRequest.of(pagina, tamanio, Sort.by("fechaCreacion").descending());
        return comentarioActividadRepository.findByActividadId(idActividad, pageable);
    }

    @Override
    public ComentarioActividad guardarComentario(Long actividadId, Long usuarioId, String contenido) throws Exception {
        Actividad actividad = actividadRepository.findById(actividadId)
                .orElseThrow(() -> new Exception("Actividad no encontrada"));
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new Exception("Usuario no encontrado"));
        ComentarioActividad nuevoComentario = new ComentarioActividad();
        nuevoComentario.setActividad(actividad);
        nuevoComentario.setUsuario(usuario);
        nuevoComentario.setContenido(contenido);
        nuevoComentario.setFechaCreacion(LocalDateTime.now());
        return comentarioActividadRepository.save(nuevoComentario);
    }

    @Override
    public void save(ComentarioActividad comentarioActividad) {
        comentarioActividadRepository.save(comentarioActividad);
    }

    @Override
    public void eliminarComentario(Long comentarioId) {
        comentarioActividadRepository.deleteById(comentarioId);
    }

    @Override
    public ComentarioActividad obtenerComentarioPorId(Long idComentario) throws Exception {
        return comentarioActividadRepository.findById(idComentario)
                .orElseThrow(() -> new Exception("Comentario no encontrado"));
    }
}
