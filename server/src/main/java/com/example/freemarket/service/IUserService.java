package com.example.freemarket.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.freemarket.dto.UserResponse;
import com.example.freemarket.model.User;

@Service
public interface IUserService {
    UserResponse create(User user);
    UserDetails getDetailsByEmail(String email);
    User getByEmail(String email);
    UserResponse userToUserResponse(User user);
}