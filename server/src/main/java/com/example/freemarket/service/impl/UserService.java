package com.example.freemarket.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.freemarket.dto.UserRequest;
import com.example.freemarket.dto.UserResponse;
import com.example.freemarket.model.User;
import com.example.freemarket.service.IUserService;

@Service
public class UserService implements UserDetailsService, IUserService{
    public UserResponse create(User user){
        return null;
    }

    public List<UserResponse> findAllUsers(){
        List<UserResponse> response = new ArrayList<>();

        //TODO

        return response;
    }
    
    public String softDeleteUser(long id){
        //TODO
        return "User not deleted";
    }
    
    public User updateUser(Long id, UserRequest userRequest){
        User user = new User();

        return user;
    }

    public UserDetails loadUserByEmail(String email){
        return new org.springframework.security.core.userdetails.User(email, email, null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }
}
