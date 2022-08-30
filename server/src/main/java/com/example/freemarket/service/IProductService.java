package com.example.freemarket.service;

import org.springframework.stereotype.Service;

import com.example.freemarket.model.Product;

@Service
public interface IProductService {
    Product create(Product product);
}
