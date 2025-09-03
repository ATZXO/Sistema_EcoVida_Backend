package com.ecovida.web.controller;

import com.ecovida.web.entities.Actividad;
import com.ecovida.web.entities.ComentarioActividad;
import com.ecovida.web.entities.UsuarioActividad;
import com.ecovida.web.service.ActividadService;
import com.ecovida.web.service.ComentarioActividadService;
import com.ecovida.web.service.UsuarioActividadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/actividad")
public class ActividadController {

    private final ActividadService actividadService;
    private final UsuarioActividadService usuarioActividadService;
    private final ComentarioActividadService comentarioActividadService;

    @PostMapping("/save")
    public ResponseEntity<Actividad> guardarActividad(@RequestBody Actividad actividad) {
        actividad.setEstado("INACTIVO");
        actividadService.save(actividad);
        return ResponseEntity.ok(actividad);
    }

    @PostMapping("/save/favorito/{idUsuario}/{idActividad}")
    public ResponseEntity<UsuarioActividad> guardarFavorito(@PathVariable Long idUsuario, @PathVariable Long idActividad) throws Exception {
        UsuarioActividad favorito = usuarioActividadService.guardarFavorito(idUsuario, idActividad);
        return ResponseEntity.ok(favorito);
    }

    @PostMapping("/save/comentario/{idActividad}/{idUsuario}")
    public ResponseEntity<?> guardarComentario(@PathVariable Long idActividad, @PathVariable Long idUsuario, @RequestBody Map<String, String> payload) throws Exception {
        String contenido = payload.get("contenido");
        ComentarioActividad comentario = comentarioActividadService.guardarComentario(idActividad, idUsuario, contenido);
        return ResponseEntity.ok(comentario);
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<Actividad>> obtenerTodos() {
        List<Actividad> actividad = actividadService.findAll();
        return ResponseEntity.ok(actividad);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Actividad> obtenerPorId(@PathVariable Long id) {
        Optional<Actividad> actividad = actividadService.findById(id);
        return actividad.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/find/all/estado")
    public ResponseEntity<List<Actividad>> obtenerActividadesPorEstado(@RequestParam String estado) {
        List<Actividad> actividad = actividadService.findByEstado(estado);
        return ResponseEntity.ok(actividad);
    }

    @GetMapping("/find/actividades/favorito/{idUsuario}")
    public ResponseEntity<List<Actividad>> obtenerActividadPorUsuario(@PathVariable Long idUsuario) {
        List<Actividad> actividad = usuarioActividadService.obtenerFavoritosPorUsuario(idUsuario);
        return ResponseEntity.ok(actividad);
    }

    @GetMapping("/find/actividad/favorito/{idUsuario}/{idActividad}")
    public ResponseEntity<Boolean> existeFavorito(@PathVariable Long idUsuario, @PathVariable Long idActividad) {
        boolean existe = usuarioActividadService.existeFavorito(idUsuario, idActividad);
        return ResponseEntity.ok(existe);
    }

    @GetMapping("/find/comentarios/{idActividad}")
    public ResponseEntity<Page<ComentarioActividad>> obtenerComentariosPorActividad(@PathVariable Long idActividad, @RequestParam(defaultValue = "0") int pagina, @RequestParam(defaultValue = "5") int tamanio) {
        Page<ComentarioActividad> comentarios = comentarioActividadService.obtenerComentariosPorActividad(idActividad, pagina, tamanio);
        return ResponseEntity.ok(comentarios);
    }

    @GetMapping("/find/asistentes/{idActividad}")
    public ResponseEntity<List<UsuarioActividad>> obtenerAsistentesPorActividad(@PathVariable Long idActividad) {
        List<UsuarioActividad> asistentes = usuarioActividadService.obtenerAsistentesPorActividad(idActividad);
        return ResponseEntity.ok(asistentes);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarActividad(@PathVariable Long id) {
        actividadService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/favorito/{idUsuario}/{idActividad}")
    public ResponseEntity<Void> eliminarFavorito(@PathVariable Long idUsuario, @PathVariable Long idActividad) throws Exception {
        usuarioActividadService.eliminarFavorito(idUsuario, idActividad);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/comentario/{idComentario}")
    public ResponseEntity<Void> eliminarComentario(@PathVariable Long idComentario) throws Exception {
        comentarioActividadService.eliminarComentario(idComentario);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> actualizarActividad(@PathVariable Long id, @RequestBody Actividad actividad) {
        Optional<Actividad> actividadOptional = actividadService.findById(id);

        if(actividadOptional.isPresent()) {
            Actividad actividadNueva = actividadOptional.get();
            actividadNueva.setTitulo(actividad.getTitulo());
            actividadNueva.setDescripcion(actividad.getDescripcion());
            actividadNueva.setFecha(actividad.getFecha());
            actividadNueva.setDistrito(actividad.getDistrito());

            actividadService.save(actividadNueva);
            return ResponseEntity.ok(actividadNueva);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/update/estado/{id}")
    public ResponseEntity<Actividad> actualizarEstado(@PathVariable Long id) {
        Actividad actividad = actividadService.actualizarEstado(id);
        return ResponseEntity.ok(actividad);
    }

    @PutMapping("/update/comentario/{idComentario}")
    public ResponseEntity<?> actualizarComentario(@PathVariable Long idComentario, @RequestBody String contenido) throws Exception {
        ComentarioActividad comentario = comentarioActividadService.obtenerComentarioPorId(idComentario);
        if (comentario != null) {
            comentario.setContenido(contenido);
            comentario.setFechaCreacion(LocalDateTime.now());
            comentarioActividadService.save(comentario);
            return ResponseEntity.ok(comentario);
        }
        return ResponseEntity.notFound().build();
    }
}
