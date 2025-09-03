package com.ecovida.web.repository;

import com.ecovida.web.entities.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioTiendaRepository extends JpaRepository<UsuarioTienda, Long> {
    List<UsuarioTienda> findByUsuarios(@NotNull Usuario usuarios);
    UsuarioTienda findByUsuariosAndTiendaIdTienda(@NotNull Usuario usuarios, @NotNull Tienda tiendaIdTienda);
    void deleteByUsuariosAndTiendaIdTienda(@NotNull Usuario usuarios, @NotNull Tienda tiendaIdTienda);
    boolean existsByUsuariosAndTiendaIdTienda(@NotNull Usuario usuarios, @NotNull Tienda tiendaIdTienda);
}
