package com.example.freemarket.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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
import com.example.freemarket.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = ProductController.class)
public class ProductControllerTest extends SecurityEnabledSetup{
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean(name="productService")
    IProductService productService;

    @MockBean(name="userService")
    IUserService userService;

    @MockBean
    ICategoryService categoryService;

    @Test
    @WithMockUser(username = "jleanon@email.com", password = "1234", authorities = { "ROLE_USER" })
    void create_validProduct_returnOk() throws Exception {
        Role role = new Role("ROLE_USER");
        User user = new User("John", "Leanon", "jleanon@email.com", "1234", role);
        Category category = new Category("CPU");
        ProductRequest productRequest = new ProductRequest("CPU AMD 5600X", BigDecimal.valueOf(230), 1L);
        Product product = new Product("CPU AMD 5600X", BigDecimal.valueOf(230), user, category);

        given(userService.getByEmail("jleanon@email.com"))
            .willReturn(user);
        given(productService.create(product))
            .willReturn(product);
        given(categoryService.getById(1L))
            .willReturn(category);

        String json = mapper.writeValueAsString(productRequest);
        mockMvc.perform(post("/products/create")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
            )
            .andDo(print())
            .andExpect(status().is2xxSuccessful());
    }
}
