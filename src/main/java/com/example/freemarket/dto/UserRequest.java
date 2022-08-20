package com.example.freemarket.dto;

import com.example.freemarket.model.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
public class UserRequest {
    private String name;
    private String lastname;
    private String email;
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;
    private Role role;
    @JsonProperty(access = Access.WRITE_ONLY)
    private String roleName;
}
