package com.example.freemarket.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.freemarket.model.Product;

@Service
public interface IProductService {
    Product create(Product product);
    Product getById(Long id);
    List<Product> getAll();
}
