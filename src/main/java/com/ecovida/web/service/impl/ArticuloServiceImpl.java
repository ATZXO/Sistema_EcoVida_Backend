package com.ecovida.web.service.impl;

import com.ecovida.web.entities.Articulo;
import com.ecovida.web.repository.ArticuloRepository;
import com.ecovida.web.service.ArticuloService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticuloServiceImpl implements ArticuloService {

    private final ArticuloRepository articuloRepository;

    @Override
    public List<Articulo> findAll() {
        return articuloRepository.findAll();
    }

    @Override
    public Optional<Articulo> findById(Long id) {
        return articuloRepository.findById(id);
    }

    @Override
    public List<Articulo> findByEstado(String estado) {
        return articuloRepository.findByEstado(estado);
    }

    @Override
    public void save(Articulo articulo) {
        articuloRepository.save(articulo);
    }

    @Override
    public Articulo actualizarArticulo(Articulo articulo) {
        Articulo articuloActual = articuloRepository.findById(articulo.getId()).orElse(null);
        if (articuloActual != null) {
            articuloActual.setTitulo(articulo.getTitulo());
            articuloActual.setDescripcion(articulo.getDescripcion());
            articuloActual.setAutor(articulo.getAutor());
            articuloActual.setFecha(articulo.getFecha());
            articuloActual.setEstado(articulo.getEstado());
            return articuloRepository.save(articuloActual);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        articuloRepository.deleteById(id);
    }

    @Override
    public Articulo actualizarEstado(Long id) {
        Articulo articulo = articuloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artículo no encontrado"));
        articulo.setEstado("ACTIVO");
        return articuloRepository.save(articulo);
    }
}
