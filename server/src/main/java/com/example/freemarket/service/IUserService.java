package com.example.freemarket.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.freemarket.dto.UserRequest;
import com.example.freemarket.dto.UserResponse;
import com.example.freemarket.model.User;

@Service
public interface IUserService {
    UserResponse create(User user);
    List<UserResponse> findAllUsers();
    String softDeleteUser(long id);
    User updateUser(Long id, UserRequest userRequest);
    UserDetails loadUserByEmail(String email);
}