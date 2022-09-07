package com.example.freemarket.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.freemarket.model.Category;
import com.example.freemarket.repository.CategoryRepository;
import com.example.freemarket.service.ICategoryService;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public Category create(Category category){
        categoryRepository.save(category);
        return category;
    }
}
