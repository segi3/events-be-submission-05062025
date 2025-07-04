package com.nizar.dansproevent.repositories;

import com.nizar.dansproevent.models.Role;
import com.nizar.dansproevent.models.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleEnum name);
}
