package com.ecovida.web.service;

import com.ecovida.web.entities.Articulo;
import com.ecovida.web.entities.UsuarioArticulo;

import java.util.List;

public interface UsuarioArticuloService {
    List<Articulo> obtenerFavoritosPorUsuario(Long idUsuario);
    UsuarioArticulo guardarFavorito(Long idUsuario, Long idArticulo) throws Exception;
    void eliminarFavorito(Long idUsuario, Long idArticulo) throws Exception;
    boolean existeFavorito(Long idUsuario, Long idArticulo);
}
