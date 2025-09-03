package com.ecovida.web.service.impl;

import com.ecovida.web.entities.Actividad;
import com.ecovida.web.repository.ActividadRepository;
import com.ecovida.web.service.ActividadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ActividadServiceImpl implements ActividadService {

    private final ActividadRepository actividadRepository;

    @Override
    public List<Actividad> findAll() {
        return actividadRepository.findAll();
    }

    @Override
    public Optional<Actividad> findById(Long id) {
        return actividadRepository.findById(id);
    }

    @Override
    public List<Actividad> findByEstado(String estado) {
        return actividadRepository.findByEstado(estado);
    }

    @Override
    public void save(Actividad actividad) {
        actividadRepository.save(actividad);
    }

    @Override
    public Actividad actualizarActividad(Actividad actividad) {
        Actividad actividadActual = actividadRepository.findById(actividad.getId()).orElse(null);
        if (actividadActual != null) {
            actividadActual.setTitulo(actividad.getTitulo());
            actividadActual.setDescripcion(actividad.getDescripcion());
            actividadActual.setFecha(actividad.getFecha());
            actividadActual.setDistrito(actividad.getDistrito());
            return actividadRepository.save(actividadActual);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        actividadRepository.deleteById(id);
    }

    @Override
    public Actividad actualizarEstado(Long id) {
        Actividad actividad = actividadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Actividad no encontrada"));
        actividad.setEstado("ACTIVO");
        return actividadRepository.save(actividad);
    }
}
