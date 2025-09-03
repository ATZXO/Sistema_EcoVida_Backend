package com.ecovida.web.repository;

import com.ecovida.web.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Roles, Long> {

}
