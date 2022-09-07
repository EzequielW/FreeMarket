package com.example.freemarket.service.impl;

import java.util.Optional;

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

    @Override
    public Category getById(Long id) {
        Category category = null;
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        
        if(optionalCategory.isPresent()){
            category = optionalCategory.get();
        }

        return category;
    }
}
