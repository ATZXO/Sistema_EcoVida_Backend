package com.ecovida.web.controller;

import com.ecovida.web.entities.*;
import com.ecovida.web.service.UsuarioService;
import com.ecovida.web.service.impl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserDetailsServiceImpl userDetailsService;

    @PostMapping("/")
    public Usuario guardarUsuario(@RequestBody Usuario usuario) throws Exception{
        usuario.setPerfil("default.png");
        usuario.setEnabled(true);
        usuario.setPassword(this.bCryptPasswordEncoder.encode(usuario.getPassword()));
        Set<UsuarioRol> usuarioRoles = new HashSet<>();

        Roles rol = new Roles();
        rol.setId(2);
        rol.setNombre("NORMAL");

        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuarios(usuario);
        usuarioRol.setRolesRol(rol);

        usuarioRoles.add(usuarioRol);
        return usuarioService.guardarUsuario(usuario,usuarioRoles);
    }

    @GetMapping("/{username}")
    public Usuario obtenerUsuario(@PathVariable("username") String username){
        return usuarioService.obtenerUsuario(username);
    }

    @DeleteMapping("/{usuarioId}")
    public void eliminarUsuario(@PathVariable("usuarioId") Long usuarioId){
        usuarioService.eliminarUsuario(usuarioId);
    }

    @PatchMapping("/update/{usuarioId}")
    public ResponseEntity<Usuario> actualizarDatosUsuario(@PathVariable("usuarioId") Long usuarioId, @RequestBody UsuarioDetallesDTO usuarioDetallesDTO) throws Exception {
        usuarioService.actualizarDatosUsuario(usuarioId, usuarioDetallesDTO);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/update/password/{usuarioId}")
    public ResponseEntity<Usuario> actualizarPasswordUsuario(@PathVariable("usuarioId") Long usuarioId, @RequestBody UsuarioPasswordDTO usuarioPasswordDTO) throws Exception {
        usuarioService.actualizarPasswordUsuario(usuarioId, usuarioPasswordDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/verificar/password")
    public ResponseEntity<?> verificarPassword(@RequestBody Map<String, String> body, Principal principal) {
        String passwordIngresada = body.get("password");
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        boolean passwordValida = bCryptPasswordEncoder.matches(passwordIngresada, userDetails.getPassword());
        if (passwordValida) {
            return ResponseEntity.ok(Map.of("valid", true));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("valid", false, "message", "Contraseña incorrecta"));
        }
    }
}
