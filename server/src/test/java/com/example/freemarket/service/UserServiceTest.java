package com.example.freemarket.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.freemarket.dto.UserResponse;
import com.example.freemarket.model.Role;
import com.example.freemarket.model.User;
import com.example.freemarket.repository.UserRepository;
import com.example.freemarket.service.impl.UserService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
public class UserServiceTest{
    @Autowired
    IUserService userService;

    @Configuration
    public static class Config {
        @Bean
        public IUserService getUserService() {
            return new UserService();
        }
    }

    @MockBean
    UserRepository userRepository;

    Role userRole;
    User user;
    UserResponse userResponse;

    @BeforeEach
    void setUp(){
        userRole = new Role("ROLE_USER");

        user = new User("John", "Leanon", 
            "jleanon@email.com", "1234", userRole);

        userResponse = new UserResponse(1L, "John", 
            "Leanon", "jleanon@email.com", userRole);
    }

    @Test
    void create_validUser_returnUserResponse(){
        user.setId(1L);

        assertEquals(userResponse, userService.create(user));
    }
}