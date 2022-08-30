package com.example.freemarket.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;

import com.example.freemarket.SecurityEnabledSetup;
import com.example.freemarket.dto.LoginRequest;
import com.example.freemarket.dto.RegistrationRequest;
import com.example.freemarket.dto.UserResponse;
import com.example.freemarket.model.Role;
import com.example.freemarket.service.IAuthService;
import com.example.freemarket.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = AuthController.class)
public class AuthControllerTest extends SecurityEnabledSetup {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean(name="authService")
    IAuthService authService;

    @MockBean(name="userService")
    IUserService userService;

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

    @Test
    void login_validUser_returnOk() throws Exception {
        LoginRequest loginRequest = new LoginRequest("jleanon@email.com", "1234");
        Role userRole = new Role("ROLE_USER");
        UserResponse userResponse = new UserResponse(1L, "John", 
            "Leanon", "jleanon@email.com", userRole);
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(userRole.getName()));
        UserDetails userDetails = new org.springframework.security.core.userdetails.User("jleanon@email.com", "1234", authorities);

        given(authService.login(loginRequest))
            .willReturn(userResponse);
        given(userService.getDetailsByEmail("jleanon@email.com"))
            .willReturn(userDetails);
        given(jwtUtil.generateToken(userDetails))
            .willReturn("examplejwt");

        String json = mapper.writeValueAsString(loginRequest);
        mockMvc.perform(post("/auth/login")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
            )
            .andDo(print())
            .andExpect(status().is2xxSuccessful());
    }
}
