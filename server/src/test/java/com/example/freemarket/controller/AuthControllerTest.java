package com.example.freemarket.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.freemarket.SecurityEnabledSetup;
import com.example.freemarket.dto.RegistrationRequest;
import com.example.freemarket.dto.UserResponse;
import com.example.freemarket.model.Role;
import com.example.freemarket.service.IAuthService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AuthControllerTest extends SecurityEnabledSetup {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean(name="authService")
    IAuthService authService;

    @Test
    void registerUser_validUser_returnOk() throws Exception {
        RegistrationRequest registrationRequest = new RegistrationRequest(
                "John", "Leanon", "jleanon@email.com", "1234", "ROLE_USER");

        Role userRole = new Role("ROLE_USER");

        UserResponse userResponse = new UserResponse(1L, "John", 
            "Leanon", "jleanon@email.com", userRole);

        given(authService.register(registrationRequest))
            .willReturn(userResponse);

        String json = mapper.writeValueAsString(registrationRequest);
        mockMvc.perform(post("/auth/register")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
            )
            .andDo(print())
            .andExpect(status().is2xxSuccessful());
    }
}
