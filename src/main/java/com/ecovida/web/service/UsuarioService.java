package com.ecovida.web.service;

import com.ecovida.web.entities.Usuario;
import com.ecovida.web.entities.UsuarioDetallesDTO;
import com.ecovida.web.entities.UsuarioPasswordDTO;
import com.ecovida.web.entities.UsuarioRol;

import java.util.Set;

public interface UsuarioService {
    Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception;

    Usuario obtenerUsuario(String username);

    void eliminarUsuario(Long usuarioId);

    Usuario obtenerUsuarioPorId(Long usuarioId) throws Exception;

    void actualizarDatosUsuario(Long usuarioId, UsuarioDetallesDTO usuarioDetallesDTO) throws Exception;

    void actualizarPasswordUsuario(Long usuarioId, UsuarioPasswordDTO usuarioPasswordDTO) throws Exception;

}
