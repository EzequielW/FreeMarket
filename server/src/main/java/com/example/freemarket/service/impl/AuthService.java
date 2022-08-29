package com.example.freemarket.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    IUserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
 
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
            String encodedPassword = bCryptPasswordEncoder.encode(request.getPassword());
            User user = new User(request.getName(), request.getLastname(), request.getEmail(), encodedPassword, role);

            userResponse = userService.create(user);
        }
        
        return userResponse;
    }
    
    public UserResponse login(LoginRequest loginRequest){
        UserResponse userResponse = null;

        try{
            Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
            if(user.isPresent()){
                System.out.println("Userpassword " + user.get());
                if(bCryptPasswordEncoder.matches(loginRequest.getPassword(), user.get().getPassword())){
                    userResponse = userService.userToUserResponse(user.get());
                }
            }
        } catch (Exception e){
            System.out.println("UserLogin " + e.getMessage());
            userResponse = null;
        }

        return userResponse;
    }
}