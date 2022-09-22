package com.example.freemarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.freemarket.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryId(Long categoryId);
}
