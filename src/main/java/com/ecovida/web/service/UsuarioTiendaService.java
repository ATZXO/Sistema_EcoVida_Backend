package com.ecovida.web.service;

import com.ecovida.web.entities.Tienda;
import com.ecovida.web.entities.UsuarioTienda;

import java.util.List;

public interface UsuarioTiendaService {
    List<Tienda> obtenerFavoritosPorUsuario(Long idUsuario);
    UsuarioTienda guardarFavorito(Long idUsuario, Long idTienda) throws Exception;
    void eliminarFavorito(Long idUsuario, Long idTienda) throws Exception;
    boolean existeFavorito(Long idUsuario, Long idTienda) throws Exception;
}
