package com.nizar.dansproevent.services;

import com.nizar.dansproevent.models.Role;
import com.nizar.dansproevent.models.RoleEnum;
import com.nizar.dansproevent.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public Optional<Role> findByName(RoleEnum name) {
        return roleRepository.findByName(name);
    }
}
