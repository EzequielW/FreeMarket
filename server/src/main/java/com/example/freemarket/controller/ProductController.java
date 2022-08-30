package com.example.freemarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.freemarket.dto.ProductRequest;
import com.example.freemarket.model.Product;
import com.example.freemarket.model.User;
import com.example.freemarket.service.IProductService;
import com.example.freemarket.service.IUserService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/products")
public class ProductController {
    @Autowired
    IProductService productService;

    @Autowired
    IUserService userService;

    @Operation(summary="Adds a new selling product for the logged user.")
    @PostMapping(path = "/create")
    public ResponseEntity<Object> create(@RequestBody ProductRequest productRequest, Authentication authentication) {
        User user = userService.getByEmail(authentication.getName());
        Product newProduct = new Product(productRequest.getName(), productRequest.getPrice(), user);
        
        Product product = productService.create(newProduct);

        if(product != null){
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Request");
        }
    }
}
