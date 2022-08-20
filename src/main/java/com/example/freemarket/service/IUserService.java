package com.example.freemarket.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.example.freemarket.dto.UserRequest;
import com.example.freemarket.dto.UserResponse;
import com.example.freemarket.model.User;

public interface IUserService {
    public List<UserResponse> findAllUsers();
    public String softDeleteUser(long id);
    public User updateUser(Long id, UserRequest userRequest);
    public UserDetails loadUserByEmail(String email);
}