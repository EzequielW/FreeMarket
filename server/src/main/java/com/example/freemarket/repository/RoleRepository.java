package com.example.freemarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.freemarket.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    public Role findByName(String roleName);
}