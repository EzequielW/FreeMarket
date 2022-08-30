package com.example.freemarket.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.freemarket.model.Product;
import com.example.freemarket.repository.ProductRepository;
import com.example.freemarket.service.IProductService;

@Service
public class ProductService implements IProductService{
    @Autowired
    ProductRepository productRepository;

    public Product create(Product product){
        productRepository.save(product);
        return product;
    }
}
