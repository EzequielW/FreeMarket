package com.example.freemarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.freemarket.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {   
}
