package com.ecovida.web.repository;

import com.ecovida.web.entities.ComentarioTienda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioTiendaRepository extends JpaRepository<ComentarioTienda, Long> {
      Page<ComentarioTienda> findByTiendaId(Long tiendaId, Pageable pageable);
}
