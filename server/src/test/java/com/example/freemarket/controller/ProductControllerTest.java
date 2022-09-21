package com.example.freemarket.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.example.freemarket.SecurityEnabledSetup;
import com.example.freemarket.dto.ProductRequest;
import com.example.freemarket.model.Category;
import com.example.freemarket.model.Product;
import com.example.freemarket.model.Role;
import com.example.freemarket.model.User;
import com.example.freemarket.service.ICategoryService;
import com.example.freemarket.service.IProductService;
import com.example.freemarket.util.FileStorageUtil;

@WebMvcTest(controllers = ProductController.class)
public class ProductControllerTest extends SecurityEnabledSetup{
    @Autowired
    private MockMvc mockMvc;

    @MockBean(name="productService")
    IProductService productService;

    @MockBean
    ICategoryService categoryService;

    @MockBean
    FileStorageUtil fileStorageUtil;

    User user;
    Category category;
    MockMultipartFile file;
    ProductRequest productRequest;
    Product product;
    List<Product> products;

    @BeforeEach
    void setUp(){
        Role role = new Role("ROLE_USER");
        user = new User("John", "Leanon", "jleanon@email.com", "1234", role);
        category = new Category("CPU");
        file = new MockMultipartFile("product", "product.jpg", "image/jpeg", "null".getBytes());
        productRequest = new ProductRequest("CPU AMD 5600X", BigDecimal.valueOf(230.20), 1L, file);
        product = new Product("CPU AMD 5600X", BigDecimal.valueOf(230.20), user, category);
        product.setImagePath("/products/product.jpg");
        products = new ArrayList<>();
        products.add(product);
    }

    @Test
    @WithMockUser(username = "jleanon@email.com", password = "1234", authorities = { "ROLE_USER" })
    void create_validProduct_returnOk() throws Exception {
        given(userService.getByEmail("jleanon@email.com"))
            .willReturn(user);
        given(productService.create(product))
            .willReturn(product);
        given(categoryService.getById(1L))
            .willReturn(category);
        given(fileStorageUtil.writeToFile(file.getBytes(), file.getOriginalFilename()))
            .willReturn("/products/product.jpg");

        mockMvc.perform(post("/products")
                .contentType("multipart/form-data")
                .flashAttr("productRequest", productRequest)
            )
            .andDo(print())
            .andExpect(status().is2xxSuccessful());
    }

    @Test
    @WithMockUser(username = "jleanon@email.com", password = "1234", authorities = { "ROLE_USER" })
    void getAll_validRequest_returnOk() throws Exception {
        given(productService.getAll())
            .willReturn(products);

        mockMvc.perform(get("/products"))
            .andDo(print())
            .andExpect(status().is2xxSuccessful());
    }
}
