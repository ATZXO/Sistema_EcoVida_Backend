package com.ecovida.web.repository;

import com.ecovida.web.entities.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticuloRepository extends JpaRepository<Articulo, Long>{
    List<Articulo> findByEstado(String estado);
}
