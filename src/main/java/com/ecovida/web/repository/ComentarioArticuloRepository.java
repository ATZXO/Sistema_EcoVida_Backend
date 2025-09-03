package com.ecovida.web.repository;

import com.ecovida.web.entities.ComentarioArticulo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ComentarioArticuloRepository extends JpaRepository<ComentarioArticulo, Long> {
    Page<ComentarioArticulo> findByArticuloId(Long articuloId, Pageable pageable);
}
