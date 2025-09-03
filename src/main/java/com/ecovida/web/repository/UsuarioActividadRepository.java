package com.ecovida.web.repository;

import com.ecovida.web.entities.Actividad;
import com.ecovida.web.entities.Usuario;
import com.ecovida.web.entities.UsuarioActividad;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioActividadRepository extends JpaRepository<UsuarioActividad, Long> {
    List<UsuarioActividad> findByUsuarios(@NotNull Usuario usuarios);
    List<UsuarioActividad> findByActividadIdActividad_Id(Long actividadIdActividadId);
    UsuarioActividad findByUsuariosAndActividadIdActividad(@NotNull Usuario usuarios, @NotNull Actividad actividadIdActividad);
    void deleteByUsuariosAndActividadIdActividad(@NotNull Usuario usuarios, @NotNull Actividad actividadIdActividad);
    boolean existsByUsuariosAndActividadIdActividad(@NotNull Usuario usuarios, @NotNull Actividad actividadIdActividad);
}
