package com.jakubeeee.security.persistence.repositories;

import com.jakubeeee.security.persistence.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

}
