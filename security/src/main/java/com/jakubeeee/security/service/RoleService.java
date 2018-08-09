package com.jakubeeee.security.service;

import com.jakubeeee.security.persistence.entities.Role;
import com.jakubeeee.security.persistence.entities.User;
import com.jakubeeee.security.persistence.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public void grantRoles(User user, Role.Type... roleTypes) {
        Set<Role> roles = new HashSet<>();
        for (Role.Type roleType : roleTypes)
            roles.add(findById(roleType.getId()));
        user.setRoles(roles);
    }

    public Role findById(Long id) {
        Optional<Role> roleO = roleRepository.findById(id);
        return roleO.orElseThrow(() -> new NoSuchElementException("Role with id " + id + " not found in the database"));
    }
}
