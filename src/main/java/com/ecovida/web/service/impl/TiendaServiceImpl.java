package com.ecovida.web.service.impl;

import com.ecovida.web.entities.Tienda;
import com.ecovida.web.repository.TiendaRepository;
import com.ecovida.web.service.TiendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TiendaServiceImpl implements TiendaService{

    private final TiendaRepository tiendaRepository;

    @Override
    public List<Tienda> findAll() {
        return tiendaRepository.findAll();
    }

    @Override
    public Optional<Tienda> findById(Long id) {
        return tiendaRepository.findById(id);
    }

    @Override
    public void save(Tienda tienda) {
        tiendaRepository.save(tienda);
    }

    @Override
    public Tienda actualizarTienda(Tienda tienda) {
        Tienda tiendaActual = tiendaRepository.findById(tienda.getId()).orElse(null);
        if (tiendaActual != null) {
            tiendaActual.setNombre(tienda.getNombre());
            tiendaActual.setDescripcion(tienda.getDescripcion());
            tiendaActual.setLugar(tienda.getLugar());
            return tiendaRepository.save(tiendaActual);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        tiendaRepository.deleteById(id);
    }
}
