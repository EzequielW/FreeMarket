package com.example.freemarket.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.freemarket.model.Category;

@Service
public interface ICategoryService {
    Category create(Category category);
    Category getById(Long id);
    List<Category> getAll();
}
