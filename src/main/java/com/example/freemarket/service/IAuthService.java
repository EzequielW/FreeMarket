package com.example.freemarket.service;

import org.springframework.http.ResponseEntity;

import com.example.freemarket.dto.LoginRequest;
import com.example.freemarket.dto.RegistrationRequest;
import com.example.freemarket.dto.UserResponse;

public interface IAuthService {
    public ResponseEntity<Object> register(RegistrationRequest request);
    public ResponseEntity<Object> login(LoginRequest loginRequest);
    public UserResponse findByEmail(String email);
    public LoginRequest createLoginRequest(RegistrationRequest reqModel);
}