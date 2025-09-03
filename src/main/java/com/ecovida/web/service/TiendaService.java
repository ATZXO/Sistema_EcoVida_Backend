package com.ecovida.web.service;

import com.ecovida.web.entities.Tienda;

import java.util.List;
import java.util.Optional;

public interface TiendaService {
    List<Tienda> findAll();
    Optional<Tienda> findById(Long id);
    void save(Tienda tienda);
    Tienda actualizarTienda(Tienda tienda);
    void delete(Long id);
}
