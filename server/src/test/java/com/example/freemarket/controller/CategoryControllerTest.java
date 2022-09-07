package com.example.freemarket.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.example.freemarket.SecurityEnabledSetup;
import com.example.freemarket.model.Category;
import com.example.freemarket.service.ICategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = CategoryController.class)
public class CategoryControllerTest extends SecurityEnabledSetup{
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    ICategoryService categoryService;

    Category category;

    @BeforeEach
    void setUp(){
        category = new Category("Furniture");
    }

    @Test
    @WithMockUser(username = "jleanon@email.com", password = "1234", authorities = { "ROLE_ADMIN" })
    void create_validCategory_returnOk() throws Exception {
        given(categoryService.create(category))
            .willReturn(category);

        String json = mapper.writeValueAsString(category);
        mockMvc.perform(post("/categories/create")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
            )
            .andDo(print())
            .andExpect(status().is2xxSuccessful());
    }

    @Test
    @WithMockUser(username = "jleanon@email.com", password = "1234", authorities = { "ROLE_USER" })
    void create_invalidRole_returnUnauthorized() throws Exception {
        given(categoryService.create(category))
            .willReturn(category);

        String json = mapper.writeValueAsString(category);
        mockMvc.perform(post("/categories/create")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
            )
            .andDo(print())
            .andExpect(status().is4xxClientError());
    }
}
