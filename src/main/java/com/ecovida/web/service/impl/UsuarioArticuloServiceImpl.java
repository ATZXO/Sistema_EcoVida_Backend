package com.ecovida.web.service.impl;

import com.ecovida.web.entities.Articulo;
import com.ecovida.web.entities.Usuario;
import com.ecovida.web.entities.UsuarioArticulo;
import com.ecovida.web.repository.ArticuloRepository;
import com.ecovida.web.repository.UsuarioArticuloRepository;
import com.ecovida.web.repository.UsuarioRepository;
import com.ecovida.web.service.UsuarioArticuloService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioArticuloServiceImpl implements UsuarioArticuloService {

    private final UsuarioArticuloRepository usuarioArticuloRepository;
    private final UsuarioRepository usuarioRepository;
    private final ArticuloRepository articuloRepository;

    @Override
    public List<Articulo> obtenerFavoritosPorUsuario(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return usuarioArticuloRepository.findByUsuarios(usuario)
                .stream()
                .map(UsuarioArticulo::getArticuloIdArticulo)
                .toList();
    }

    @Override
    public UsuarioArticulo guardarFavorito(Long idUsuario, Long idArticulo) throws Exception {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Articulo articulo = articuloRepository.findById(idArticulo)
                .orElseThrow(() -> new RuntimeException("Articulo no encontrado"));

        UsuarioArticulo favoritoExistente = usuarioArticuloRepository.findByUsuariosAndArticuloIdArticulo(usuario, articulo);

        if(favoritoExistente != null){
            System.out.println("El articulo ya es favorito");
            throw new Exception("El articulo ya es favorito");
        }
        else{
            UsuarioArticulo favorito = UsuarioArticulo.builder()
                    .usuarios(usuario)
                    .articuloIdArticulo(articulo)
                    .build();
            return usuarioArticuloRepository.save(favorito);
        }
    }

    @Override
    @Transactional
    public void eliminarFavorito(Long idUsuario, Long idArticulo) throws Exception {
        usuarioArticuloRepository.deleteByUsuariosAndArticuloIdArticulo(
                usuarioRepository.findById(idUsuario)
                        .orElseThrow(() -> new RuntimeException("Usuario no encontrado")),
                articuloRepository.findById(idArticulo)
                        .orElseThrow(() -> new RuntimeException("Articulo no encontrado"))
        );
    }

    @Override
    public boolean existeFavorito(Long idUsuario, Long idArticulo) {
        return usuarioArticuloRepository.existsByUsuariosAndArticuloIdArticulo(
                usuarioRepository.findById(idUsuario)
                        .orElseThrow(() -> new RuntimeException("Usuario no encontrado")),
                articuloRepository.findById(idArticulo)
                        .orElseThrow(() -> new RuntimeException("Articulo no encontrado"))
        );
    }
}
