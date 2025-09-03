package com.ecovida.web.service;

import com.ecovida.web.entities.Articulo;

import java.util.List;
import java.util.Optional;

public interface ArticuloService {
    List<Articulo> findAll();
    Optional<Articulo> findById(Long id);
    List<Articulo> findByEstado(String estado);
    void save(Articulo articulo);
    Articulo actualizarArticulo(Articulo articulo);
    void delete(Long id);
    Articulo actualizarEstado(Long id);
}
