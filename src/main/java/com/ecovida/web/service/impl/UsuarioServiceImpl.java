package com.ecovida.web.service.impl;

import com.ecovida.web.entities.Usuario;
import com.ecovida.web.entities.UsuarioDetallesDTO;
import com.ecovida.web.entities.UsuarioPasswordDTO;
import com.ecovida.web.entities.UsuarioRol;
import com.ecovida.web.repository.RolRepository;
import com.ecovida.web.repository.UsuarioRepository;
import com.ecovida.web.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception {
        Usuario usuarioLocal = usuarioRepository.findByUsername(usuario.getUsername());
        if(usuarioLocal != null){
            System.out.println("El usuario ya existe");
            throw new Exception("El usuario ya esta presente");
        }
        else{
            for(UsuarioRol usuarioRol:usuarioRoles){
                rolRepository.save(usuarioRol.getRolesRol());
            }
            usuario.getUsuarioRoles().addAll(usuarioRoles);
            usuarioLocal = usuarioRepository.save(usuario);
        }
        return usuarioLocal;
    }

    @Override
    public Usuario obtenerUsuario(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Override
    public void eliminarUsuario(Long usuarioId) {
        usuarioRepository.deleteById(usuarioId);
    }

    @Override
    public Usuario obtenerUsuarioPorId(Long usuarioId) throws Exception {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new Exception("Usuario no encontrado con el ID: " + usuarioId));
    }

    @Override
    public void actualizarDatosUsuario(Long usuarioId, UsuarioDetallesDTO usuarioDetallesDTO) throws Exception {
        Usuario usuario = obtenerUsuarioPorId(usuarioId);
        if(usuarioDetallesDTO.getNombre() != null) {
            usuario.setNombre(usuarioDetallesDTO.getNombre());
        }
        if(usuarioDetallesDTO.getApellido() != null) {
            usuario.setApellido(usuarioDetallesDTO.getApellido());
        }
        if(usuarioDetallesDTO.getTelefono() != null) {
            usuario.setTelefono(usuarioDetallesDTO.getTelefono());
        }
        usuarioRepository.save(usuario);
    }

    @Override
    public void actualizarPasswordUsuario(Long usuarioId, UsuarioPasswordDTO usuarioPasswordDTO) throws Exception {
        Usuario usuario = obtenerUsuarioPorId(usuarioId);
        if(usuarioPasswordDTO.getPassword() != null && !usuarioPasswordDTO.getPassword().isEmpty()) {
            usuario.setPassword(this.bCryptPasswordEncoder.encode(usuarioPasswordDTO.getPassword()));
        }
        usuarioRepository.save(usuario);
    }
}
