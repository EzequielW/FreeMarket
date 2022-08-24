package com.example.freemarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.freemarket.dto.UserRequest;
import com.example.freemarket.dto.UserResponse;
import com.example.freemarket.service.IUserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    // @Autowired
    // private IUserService userService;

    // @GetMapping("/")
    // public List<UserResponse> getUserList() {
    //     return userService.findAllUsers();
    // }
    
    // @PutMapping(value = "/{id}")
    // public ResponseEntity<Object> updateUser(@PathVariable ("id") Long id, @RequestBody UserRequest user){
    //     try{
    //         return new ResponseEntity<>(userService.updateUser(id, user), HttpStatus.OK);
    //     }
    //     catch (Exception e){
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //     }
    // }

}