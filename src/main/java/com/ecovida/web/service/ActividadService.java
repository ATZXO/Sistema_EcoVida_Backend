package com.ecovida.web.service;

import com.ecovida.web.entities.Actividad;

import java.util.List;
import java.util.Optional;

public interface ActividadService {
    List<Actividad> findAll();
    Optional<Actividad> findById(Long id);
    List<Actividad> findByEstado(String estado);
    void save(Actividad actividad);
    Actividad actualizarActividad(Actividad actividad);
    void delete(Long id);
    Actividad actualizarEstado(Long id);
}
