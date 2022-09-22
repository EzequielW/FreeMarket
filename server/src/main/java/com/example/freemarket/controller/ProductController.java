package com.example.freemarket.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.freemarket.dto.ProductRequest;
import com.example.freemarket.model.Category;
import com.example.freemarket.model.Product;
import com.example.freemarket.model.User;
import com.example.freemarket.service.ICategoryService;
import com.example.freemarket.service.IProductService;
import com.example.freemarket.service.IUserService;
import com.example.freemarket.util.FileStorageUtil;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(path = "/products")
public class ProductController {
    @Autowired
    IProductService productService;

    @Autowired
    IUserService userService;

    @Autowired
    ICategoryService categoryService;

    @Autowired
    FileStorageUtil fileStorageUtil;

    @Operation(summary="Adds a new selling product for the logged user")
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<Object> create(@ModelAttribute @Valid ProductRequest productRequest, Authentication authentication) {
        User user = userService.getByEmail(authentication.getName());
        Category category = categoryService.getById(productRequest.getCategoryId());
        Product newProduct = new Product(productRequest.getName(), productRequest.getPrice(), user, category);
        
        Product product = null;
        try{
            String imagePath = fileStorageUtil.writeToFile(
                productRequest.getFile().getBytes(), 
                productRequest.getFile().getOriginalFilename());
            newProduct.setImagePath(imagePath);

            product = productService.create(newProduct);
        } catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }

        if(product != null){
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Request");
        }
    }

    @Operation(summary="Gets all products")
    @GetMapping
    public ResponseEntity<Object> getAll(@RequestParam(required = false) Long categoryId) {
        List<Product> products;

        if(categoryId != null){
            products = productService.getByCategoryId(categoryId);
        } else{
            products = productService.getAll();
        }

        return ResponseEntity.ok(products);
    }
}
