package com.example.freemarket;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.freemarket.security.JwtRequestFilter;
import com.example.freemarket.security.WebSecurityConfig;

@Import(WebSecurityConfig.class)
public abstract class SecurityEnabledSetup {
    @MockBean
    protected UserDetailsService userDetailsService;

    @MockBean
    protected JwtRequestFilter jwtRequestFilter;

    @MockBean
    protected BCryptPasswordEncoder bCryptPasswordEncoder;
}
