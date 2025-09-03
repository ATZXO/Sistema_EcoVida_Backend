package com.ecovida.web.service.impl;

import com.ecovida.web.entities.*;
import com.ecovida.web.repository.TiendaRepository;
import com.ecovida.web.repository.UsuarioRepository;
import com.ecovida.web.repository.UsuarioTiendaRepository;
import com.ecovida.web.service.UsuarioTiendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioTiendaServiceImpl implements UsuarioTiendaService {

    private final UsuarioTiendaRepository usuarioTiendaRepository;
    private final UsuarioRepository usuarioRepository;
    private final TiendaRepository tiendaRepository;

    @Override
    public List<Tienda> obtenerFavoritosPorUsuario(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return usuarioTiendaRepository.findByUsuarios(usuario)
                .stream()
                .map(UsuarioTienda::getTiendaIdTienda)
                .toList();
    }

    @Override
    public UsuarioTienda guardarFavorito(Long idUsuario, Long idTienda) throws Exception {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Tienda tienda = tiendaRepository.findById(idTienda)
                .orElseThrow(() -> new RuntimeException("Actividad no encontrada"));

        UsuarioTienda favoritoExistente = usuarioTiendaRepository.findByUsuariosAndTiendaIdTienda(usuario, tienda);

        if(favoritoExistente != null){
            System.out.println("La tienda ya es favorita");
            throw new Exception("La tienda ya es favorita");
        }
        else{
            UsuarioTienda favorito = UsuarioTienda.builder()
                    .usuarios(usuario)
                    .tiendaIdTienda(tienda)
                    .build();
            return usuarioTiendaRepository.save(favorito);
        }
    }

    @Override
    @Transactional
    public void eliminarFavorito(Long idUsuario, Long idTienda) throws Exception {
        usuarioTiendaRepository.deleteByUsuariosAndTiendaIdTienda(
                usuarioRepository.findById(idUsuario)
                        .orElseThrow(() -> new RuntimeException("Usuario no encontrado")),
                tiendaRepository.findById(idTienda)
                        .orElseThrow(() -> new RuntimeException("Actividad no encontrada"))
        );
    }

    @Override
    public boolean existeFavorito(Long idUsuario, Long idTienda) throws Exception {
        return usuarioTiendaRepository.existsByUsuariosAndTiendaIdTienda(
                usuarioRepository.findById(idUsuario)
                        .orElseThrow(() -> new RuntimeException("Usuario no encontrado")),
                tiendaRepository.findById(idTienda)
                        .orElseThrow(() -> new RuntimeException("Actividad no encontrada"))
        );
    }
}
