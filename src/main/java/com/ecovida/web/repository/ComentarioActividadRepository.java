package com.ecovida.web.repository;

import com.ecovida.web.entities.ComentarioActividad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ComentarioActividadRepository extends JpaRepository<ComentarioActividad, Long> {
    Page<ComentarioActividad> findByActividadId(Long actividadId, Pageable pageable);
}
