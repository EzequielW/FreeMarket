package com.example.freemarket.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.freemarket.model.Category;
import com.example.freemarket.model.Product;
import com.example.freemarket.model.Role;
import com.example.freemarket.model.User;
import com.example.freemarket.repository.ProductRepository;
import com.example.freemarket.service.impl.ProductService;

@ExtendWith(SpringExtension.class)
public class ProductServiceTest{
    @Autowired
    IProductService productService;

    @Configuration
    public static class Config {
        @Bean
        public IProductService getProductService() {
            return new ProductService();
        }
    }

    @MockBean
    ProductRepository productRepository;

    Product newProduct;
    User user;
    Role role;
    Category category;
    List<Product> products;

    @BeforeEach
    void setUp(){
        role = new Role("ROLE_USER");
        user = new User("John", "Leanon", "jleanon@email.com", "1234", role);
        category = new Category("CPU");
        newProduct = new Product("CPU AMD 5600x", BigDecimal.valueOf(230), user, category);
        products = new ArrayList<>();
        products.add(newProduct);
    }

    @Test
    void create_validProduct_returnProduct(){
        assertEquals(newProduct, productService.create(newProduct));
    }

    @Test
    void getAll_validRequest_returnList(){
        given(productRepository.findAll())
            .willReturn(products);

        assertEquals(products, productService.getAll());
    }
}
