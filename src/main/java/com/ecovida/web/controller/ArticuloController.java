package com.ecovida.web.controller;

import com.ecovida.web.entities.Articulo;
import com.ecovida.web.entities.ComentarioArticulo;
import com.ecovida.web.entities.UsuarioArticulo;
import com.ecovida.web.service.ArticuloService;
import com.ecovida.web.service.ComentarioArticuloService;
import com.ecovida.web.service.UsuarioArticuloService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/articulo")
@RequiredArgsConstructor
public class ArticuloController {

    private final ArticuloService articuloService;
    private final UsuarioArticuloService usuarioArticuloService;
    private final ComentarioArticuloService comentarioArticuloService;

    @PostMapping(path = "/save")
    public ResponseEntity<Articulo> guardarArticulo(@RequestBody Articulo articulo){
        articulo.setFecha(LocalDate.now());
        articulo.setEstado("INACTIVO");

        articuloService.save(articulo);

        return ResponseEntity.ok(articulo);
    }

    @PostMapping("/save/favorito/{idUsuario}/{idArticulo}")
    public ResponseEntity<UsuarioArticulo> guardarFavorito(@PathVariable Long idUsuario, @PathVariable Long idArticulo) throws Exception {
        UsuarioArticulo favorito = usuarioArticuloService.guardarFavorito(idUsuario, idArticulo);
        return ResponseEntity.ok(favorito);
    }

    @PostMapping("/save/comentario/{idArticulo}/{idUsuario}")
    public ResponseEntity<?> guardarComentario(@PathVariable Long idArticulo, @PathVariable Long idUsuario, @RequestBody Map<String, String> payload) throws Exception {
        String contenido = payload.get("contenido");
        ComentarioArticulo comentario = comentarioArticuloService.guardarComentario(idArticulo, idUsuario, contenido);
        return ResponseEntity.ok(comentario);
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<Articulo>> obtenerTodos() {
        List<Articulo> articulos = articuloService.findAll();
        return ResponseEntity.ok(articulos);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Articulo> obtenerPorId(@PathVariable Long id) {
        Optional<Articulo> articulo = articuloService.findById(id);
        return articulo.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/find/all/estado")
    public ResponseEntity<List<Articulo>> obtenerArticulosPorEstado(@RequestParam String estado) {
        List<Articulo> articulos = articuloService.findByEstado(estado);
        return ResponseEntity.ok(articulos);
    }

    @GetMapping("/find/articulos/favorito/{idUsuario}")
    public ResponseEntity<List<Articulo>> obtenerArticulosPorUsuario(@PathVariable Long idUsuario) {
        List<Articulo> articulos = usuarioArticuloService.obtenerFavoritosPorUsuario(idUsuario);
        return ResponseEntity.ok(articulos);
    }

    @GetMapping("/find/articulo/favorito/{idUsuario}/{idArticulo}")
    public ResponseEntity<Boolean> existeFavorito(@PathVariable Long idUsuario, @PathVariable Long idArticulo) {
        boolean existe = usuarioArticuloService.existeFavorito(idUsuario, idArticulo);
        return ResponseEntity.ok(existe);
    }

    @GetMapping("/find/comentarios/{idArticulo}")
    public ResponseEntity<Page<ComentarioArticulo>> obtenerComentariosPorArticulo(@PathVariable Long idArticulo, @RequestParam(defaultValue = "0") int pagina, @RequestParam(defaultValue = "5") int tamanio) {
        Page<ComentarioArticulo> comentarios = comentarioArticuloService.obtenerComentariosPorArticulo(idArticulo, pagina, tamanio);
        return ResponseEntity.ok(comentarios);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarArticulo(@PathVariable Long id) {
        articuloService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/favorito/{idUsuario}/{idArticulo}")
    public ResponseEntity<Void> eliminarFavorito(@PathVariable Long idUsuario, @PathVariable Long idArticulo) throws Exception {
        usuarioArticuloService.eliminarFavorito(idUsuario, idArticulo);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/comentario/{idComentario}")
    public ResponseEntity<Void> eliminarComentario(@PathVariable Long idComentario) throws Exception {
        comentarioArticuloService.eliminarComentario(idComentario);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> actualizarArticulo(@PathVariable Long id, @RequestBody Articulo articulo) {
        Optional<Articulo> articuloOptional = articuloService.findById(id);

        if(articuloOptional.isPresent()) {
            Articulo articuloNuevo = articuloOptional.get();
            articuloNuevo.setTitulo(articulo.getTitulo());
            articuloNuevo.setDescripcion(articulo.getDescripcion());
            articuloNuevo.setAutor(articulo.getAutor());
            articuloNuevo.setFecha(LocalDate.now());

            articuloService.save(articuloNuevo);

            return ResponseEntity.ok(articuloNuevo);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/update/estado/{id}")
    public ResponseEntity<Articulo> actualizarEstado(@PathVariable Long id) {
        Articulo articulo = articuloService.actualizarEstado(id);
        return ResponseEntity.ok(articulo);
    }

    @PutMapping("/update/comentario/{idComentario}")
    public ResponseEntity<?> actualizarComentario(@PathVariable Long idComentario, @RequestBody String contenido) throws Exception {
        ComentarioArticulo comentario = comentarioArticuloService.obtenerComentarioPorId(idComentario);
        if (comentario != null) {
            comentario.setContenido(contenido);
            comentario.setFechaCreacion(LocalDateTime.now());
            comentarioArticuloService.save(comentario);
            return ResponseEntity.ok(comentario);
        }
        return ResponseEntity.notFound().build();
    }
}
