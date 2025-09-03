package com.ecovida.web.repository;

import com.ecovida.web.entities.Articulo;
import com.ecovida.web.entities.Usuario;
import com.ecovida.web.entities.UsuarioArticulo;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioArticuloRepository extends JpaRepository<UsuarioArticulo, Long> {
    List<UsuarioArticulo> findByUsuarios(@NotNull Usuario usuarios);
    UsuarioArticulo findByUsuariosAndArticuloIdArticulo(@NotNull Usuario usuarios, @NotNull Articulo articuloIdArticulo);
    void deleteByUsuariosAndArticuloIdArticulo(@NotNull Usuario usuarios, @NotNull Articulo articuloIdArticulo);
    boolean existsByUsuariosAndArticuloIdArticulo(@NotNull Usuario usuarios, @NotNull Articulo articuloIdArticulo);
}
