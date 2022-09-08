package com.example.freemarket.service.impl;

import java.util.Optional;

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

    @Override
    public Product getById(Long id) {
        Product product = null;
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()){
            product = optionalProduct.get();
        }
        
        return product;
    }
}
