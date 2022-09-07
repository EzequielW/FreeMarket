package com.example.freemarket.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.freemarket.model.Category;
import com.example.freemarket.model.Role;
import com.example.freemarket.model.User;
import com.example.freemarket.repository.CategoryRepository;
import com.example.freemarket.service.impl.CategoryService;

@ExtendWith(SpringExtension.class)
public class CategoryServiceTest{
    @Autowired
    ICategoryService categoryService;

    @Configuration
    public static class Config {
        @Bean
        public ICategoryService getCategoryService() {
            return new CategoryService();
        }
    }

    @MockBean
    CategoryRepository categoryRepository;

    User user;
    Role adminRole;
    Role userRole;
    Category category;

    @BeforeEach
    void setUp(){
        adminRole = new Role("ROLE_ADMIN");
        userRole = new Role("ROLE_USER");
        user = new User("John", "Leanon", "jleanon@email.com", "1234", userRole);
        category = new Category("Furniture");
    }

    @Test
    void create_validCategory_returnCategory(){
        assertEquals(category, categoryService.create(category));
    }
}