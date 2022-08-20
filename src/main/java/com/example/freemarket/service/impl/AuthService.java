package com.example.freemarket.service.impl;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.freemarket.dto.LoginRequest;
import com.example.freemarket.dto.RegistrationRequest;
import com.example.freemarket.dto.UserResponse;
import com.example.freemarket.service.IAuthService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@AllArgsConstructor
@Slf4j
@Service
public class AuthService implements IAuthService {
    public ResponseEntity<Object> register(RegistrationRequest request){
        //TODO
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    public ResponseEntity<Object> login(LoginRequest loginRequest){
        //TODO
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public UserResponse findByEmail(String email){
        //TODO
        return new UserResponse();
    }

    public LoginRequest createLoginRequest(RegistrationRequest reqModel){
        //TODO
        return new LoginRequest();
    }
}