package com.example.freemarket.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.freemarket.dto.CategoryRequest;
import com.example.freemarket.model.Category;
import com.example.freemarket.service.ICategoryService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/categories")
public class CategoryController {
    @Autowired
    ICategoryService categoryService;

    @Operation(summary="Adds a new category for products")
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid CategoryRequest categoryRequest) {
        Category newCategory = new Category(categoryRequest.getName());
        Category category = categoryService.create(newCategory);

        if(category != null){
            return ResponseEntity.ok(category);
        } 
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Request");
        }
    }

    @Operation(summary="Gets all categories")
    @GetMapping
    public ResponseEntity<Object> getAll() {
        List<Category> categories = categoryService.getAll();

        return ResponseEntity.ok(categories);
    }
}
