package com.example.freemarket.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.freemarket.SecurityEnabledSetup;
import com.example.freemarket.dto.RegistrationRequest;
import com.example.freemarket.dto.UserResponse;
import com.example.freemarket.model.Role;
import com.example.freemarket.model.User;
import com.example.freemarket.repository.RoleRepository;
import com.example.freemarket.service.impl.AuthService;


@ExtendWith(SpringExtension.class)
@ContextConfiguration
public class AuthServiceTest extends SecurityEnabledSetup{
    @Autowired
    IAuthService authService;

    @MockBean
    IUserService userService;
    
    @MockBean
    RoleRepository roleRepository;

    @Configuration
    public static class Config {
        @Bean
        public IAuthService getAuthService() {
            return new AuthService();
        }
    }

    RegistrationRequest registrationRequest;
    Role userRole;
    Role adminRole;
    User user;
    UserResponse userResponse;

    @BeforeEach
    void setUp(){
        registrationRequest = new RegistrationRequest(
                "John", "Leanon", "jleanon@email.com", "1234", "ROLE_USER");

        userRole = new Role("ROLE_USER");
        adminRole = new Role("ROLE_ADMIN");

        user = new User("John", "Leanon", 
            "jleanon@email.com", "1234", userRole);

        userResponse = new UserResponse(1L, "John", 
            "Leanon", "jleanon@email.com", userRole);
    }

    @Test
    void registerUser_validUser_returnUserResponse(){
        registrationRequest.setRoleName(userRole.getName());
        System.out.println("UserObject1 " + user);
        user.setRole(userRole);
        userResponse.setRole(userRole);

        given(roleRepository.findByName(registrationRequest.getRoleName()))
            .willReturn(userRole);
        given(userService.create(user))
            .willReturn(userResponse);

        assertEquals(userResponse, authService.register(registrationRequest));
    }

    @Test
    void registerUser_invalidRole_returnNull(){
        registrationRequest.setRoleName(adminRole.getName());
        user.setRole(adminRole);
        userResponse.setRole(adminRole);

        given(roleRepository.findByName(registrationRequest.getRoleName()))
            .willReturn(adminRole);
        given(userService.create(user))
            .willReturn(userResponse);

        assertEquals(null, authService.register(registrationRequest));
    }
}
