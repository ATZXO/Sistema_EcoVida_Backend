package com.ecovida.web.service.impl;

import com.ecovida.web.entities.*;
import com.ecovida.web.repository.ActividadRepository;
import com.ecovida.web.repository.UsuarioActividadRepository;
import com.ecovida.web.repository.UsuarioRepository;
import com.ecovida.web.service.UsuarioActividadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioActividadServiceImpl implements UsuarioActividadService {

    private final UsuarioActividadRepository usuarioActividadRepository;
    private final UsuarioRepository usuarioRepository;
    private final ActividadRepository actividadRepository;

    @Override
    public List<Actividad> obtenerFavoritosPorUsuario(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return usuarioActividadRepository.findByUsuarios(usuario)
                .stream()
                .map(UsuarioActividad::getActividadIdActividad)
                .toList();
    }

    @Override
    public List<UsuarioActividad> obtenerAsistentesPorActividad(Long idActividad) {
        return usuarioActividadRepository.findByActividadIdActividad_Id(idActividad);
    }

    @Override
    public UsuarioActividad guardarFavorito(Long idUsuario, Long idActividad) throws Exception {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Actividad actividad = actividadRepository.findById(idActividad)
                .orElseThrow(() -> new RuntimeException("Actividad no encontrada"));

        UsuarioActividad favoritoExistente = usuarioActividadRepository.findByUsuariosAndActividadIdActividad(usuario, actividad);

        if(favoritoExistente != null){
            System.out.println("La actividad ya es favorito");
            throw new Exception("La actividad ya es favorito");
        }
        else{
            UsuarioActividad favorito = UsuarioActividad.builder()
                    .usuarios(usuario)
                    .actividadIdActividad(actividad)
                    .build();
            return usuarioActividadRepository.save(favorito);
        }
    }

    @Override
    @Transactional
    public void eliminarFavorito(Long idUsuario, Long idActividad) throws Exception {
        usuarioActividadRepository.deleteByUsuariosAndActividadIdActividad(
                usuarioRepository.findById(idUsuario)
                        .orElseThrow(() -> new RuntimeException("Usuario no encontrado")),
                actividadRepository.findById(idActividad)
                        .orElseThrow(() -> new RuntimeException("Actividad no encontrado"))
        );
    }

    @Override
    public boolean existeFavorito(Long idUsuario, Long idActividad) {
        return usuarioActividadRepository.existsByUsuariosAndActividadIdActividad(
                usuarioRepository.findById(idUsuario)
                        .orElseThrow(() -> new RuntimeException("Usuario no encontrado")),
                actividadRepository.findById(idActividad)
                        .orElseThrow(() -> new RuntimeException("Actividad no encontrado"))
        );
    }
}
