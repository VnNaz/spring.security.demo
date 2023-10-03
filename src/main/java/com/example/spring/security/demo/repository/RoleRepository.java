package com.example.spring.security.demo.repository;

import com.example.spring.security.demo.domain.Role;
import com.example.spring.security.demo.enums.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository  extends JpaRepository<Role, Integer> {
    public Role findRoleByAuthority(Authority authority);
}
