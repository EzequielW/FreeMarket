package com.example.freemarket;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.freemarket.security.WebSecurityConfig;
import com.example.freemarket.service.impl.UserService;
import com.example.freemarket.util.JwtUtil;

@Import(WebSecurityConfig.class)
public abstract class SecurityEnabledSetup {
    @MockBean
    protected UserService userService;

    @MockBean
    protected BCryptPasswordEncoder bCryptPasswordEncoder;

    @MockBean
    protected JwtUtil jwtUtil;
}
