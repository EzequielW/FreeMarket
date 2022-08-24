package com.example.freemarket.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.freemarket.dto.LoginRequest;
import com.example.freemarket.dto.RegistrationRequest;
import com.example.freemarket.dto.UserResponse;

@Service
public interface IAuthService {
    UserResponse register(RegistrationRequest request);
    ResponseEntity<Object> login(LoginRequest loginRequest);
}