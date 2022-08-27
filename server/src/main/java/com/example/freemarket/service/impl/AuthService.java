package com.example.freemarket.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.example.freemarket.dto.LoginRequest;
import com.example.freemarket.dto.RegistrationRequest;
import com.example.freemarket.dto.UserResponse;
import com.example.freemarket.repository.RoleRepository;
import com.example.freemarket.repository.UserRepository;
import com.example.freemarket.service.IAuthService;
import com.example.freemarket.service.IUserService;
import com.example.freemarket.model.Role;
import com.example.freemarket.model.User;

@Service
public class AuthService implements IAuthService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    IUserService userService;

    @Autowired
    UserRepository userRepository;

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
    
    public UserResponse login(LoginRequest loginRequest){
        UserResponse userResponse = null;

        try{
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
            System.out.println("UserLogin " + user);
            if(user.isPresent()){
                userResponse = this.userToUserResponse(user.get());
            }
        } catch (Exception e){
            System.out.println("UserLogin " + e.getMessage());
            userResponse = null;
        }

        return userResponse;
    }

    public UserResponse userToUserResponse(User user){
        UserResponse userResponse = new UserResponse(
            user.getId(), 
            user.getName(), 
            user.getLastname(), 
            user.getEmail(), 
            user.getRole());

        return userResponse;
    }
}