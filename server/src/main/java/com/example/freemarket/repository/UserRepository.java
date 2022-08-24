package com.example.freemarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.freemarket.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
