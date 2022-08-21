package com.example.freemarket.dto;

import com.example.freemarket.model.Role;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {
    public UserResponse() {}
	private Long id;
	private String name;
    private String lastname;
    private String email;
    private Role role;
}