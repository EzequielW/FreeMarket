package com.example.freemarket.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.freemarket.dto.LoginRequest;
import com.example.freemarket.dto.RegistrationRequest;
import com.example.freemarket.dto.UserResponse;
import com.example.freemarket.repository.RoleRepository;
import com.example.freemarket.service.IAuthService;
import com.example.freemarket.service.IUserService;
import com.example.freemarket.model.Role;
import com.example.freemarket.model.User;

@Service
public class AuthService implements IAuthService {
    @Autowired
    IUserService userService;

    @Autowired
    RoleRepository roleRepository;
 
    public UserResponse register(RegistrationRequest request){
        Boolean validUser = true;
        UserResponse userResponse = null;
        Role role = roleRepository.findByName(request.getRoleName());

        if(role == null){
            validUser = false;
        }

        if(request.getRoleName() != "ROLE_USER"){
            validUser = false;
        }

        if(validUser){
            User user = new User(request.getName(), request.getLastname(), request.getEmail(),
            request.getPassword(), role);

            userResponse = userService.create(user);
        }
        
        return userResponse;
    }
    
    public ResponseEntity<Object> login(LoginRequest loginRequest){
        //TODO
        return new ResponseEntity<>(HttpStatus.OK);
    }
}