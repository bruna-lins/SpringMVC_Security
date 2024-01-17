package com.springmvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springmvc.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByAuthority(String authority);

}
