package com.ecovida.web.controller;

import com.ecovida.web.entities.ComentarioTienda;
import com.ecovida.web.entities.Tienda;
import com.ecovida.web.entities.UsuarioTienda;
import com.ecovida.web.service.ComentarioTiendaService;
import com.ecovida.web.service.TiendaService;
import com.ecovida.web.service.UsuarioTiendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/tienda")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class TiendaController {

    private final TiendaService tiendaService;
    private final UsuarioTiendaService usuarioTiendaService;
    private final ComentarioTiendaService comentarioTiendaService;

    @PostMapping("/save")
    public ResponseEntity<Tienda> guardarTienda(@RequestBody Tienda tienda) {
        tiendaService.save(tienda);
        return ResponseEntity.ok(tienda);
    }

    @PostMapping("/save/favorito/{idUsuario}/{idTienda}")
    public ResponseEntity<UsuarioTienda> guardarFavorito(@PathVariable Long idUsuario, @PathVariable Long idTienda) throws Exception {
        UsuarioTienda favorito = usuarioTiendaService.guardarFavorito(idUsuario, idTienda);
        return ResponseEntity.ok(favorito);
    }

    @PostMapping("/save/comentario/{idTienda}/{idUsuario}")
    public ResponseEntity<?> guardarComentario(@PathVariable Long idTienda, @PathVariable Long idUsuario, @RequestBody Map<String, String> payload) throws Exception {
        String contenido = payload.get("contenido");
        ComentarioTienda comentario = comentarioTiendaService.guardarComentario(idTienda, idUsuario, contenido);
        return ResponseEntity.ok(comentario);
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<Tienda>> obtenerTodos() {
        List<Tienda> tienda = tiendaService.findAll();
        return ResponseEntity.ok(tienda);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Tienda> obtenerPorId(@PathVariable Long id) {
        Optional<Tienda> tienda = tiendaService.findById(id);
        return tienda.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/find/tiendas/favorito/{idUsuario}")
    public ResponseEntity<List<Tienda>> obtenerActividadPorUsuario(@PathVariable Long idUsuario) {
        List<Tienda> tienda = usuarioTiendaService.obtenerFavoritosPorUsuario(idUsuario);
        return ResponseEntity.ok(tienda);
    }

    @GetMapping("/find/tienda/favorito/{idUsuario}/{idTienda}")
    public ResponseEntity<Boolean> existeFavorito(@PathVariable Long idUsuario, @PathVariable Long idTienda) throws Exception {
        boolean existe = usuarioTiendaService.existeFavorito(idUsuario, idTienda);
        return ResponseEntity.ok(existe);
    }

    @GetMapping("/find/comentarios/{idTienda}")
    public ResponseEntity<Page<ComentarioTienda>> obtenerComentariosPorArticulo(@PathVariable Long idTienda, @RequestParam(defaultValue = "0") int pagina, @RequestParam(defaultValue = "5") int tamanio) {
        Page<ComentarioTienda> comentarios = comentarioTiendaService.obtenerComentariosPorTienda(idTienda, pagina, tamanio);
        return ResponseEntity.ok(comentarios);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarTienda(@PathVariable Long id) {
        tiendaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/favorito/{idUsuario}/{idTienda}")
    public ResponseEntity<Void> eliminarFavorito(@PathVariable Long idUsuario, @PathVariable Long idTienda) throws Exception {
        usuarioTiendaService.eliminarFavorito(idUsuario, idTienda);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/comentario/{idComentario}")
    public ResponseEntity<Void> eliminarComentario(@PathVariable Long idComentario) throws Exception {
        comentarioTiendaService.eliminarComentario(idComentario);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> actualizarTienda(@PathVariable Long id, @RequestBody Tienda tienda) {
        Optional<Tienda> tiendaOptional = tiendaService.findById(id);

        if(tiendaOptional.isPresent()) {
            Tienda tiendaNueva = tiendaOptional.get();
            tiendaNueva.setNombre(tienda.getNombre());
            tiendaNueva.setDescripcion(tienda.getDescripcion());
            tiendaNueva.setLugar(tienda.getLugar());

            tiendaService.save(tiendaNueva);
            return ResponseEntity.ok(tiendaNueva);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/update/comentario/{idComentario}")
    public ResponseEntity<?> actualizarComentario(@PathVariable Long idComentario, @RequestBody String contenido) throws Exception {
        ComentarioTienda comentario = comentarioTiendaService.obtenerComentarioPorId(idComentario);
        if (comentario != null) {
            comentario.setContenido(contenido);
            comentario.setFechaCreacion(LocalDateTime.now());
            comentarioTiendaService.save(comentario);
            return ResponseEntity.ok(comentario);
        }
        return ResponseEntity.notFound().build();
    }
}
