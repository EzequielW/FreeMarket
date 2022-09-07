package com.example.freemarket.service;

import org.springframework.stereotype.Service;

import com.example.freemarket.model.Category;

@Service
public interface ICategoryService {
    Category create(Category category);
}
