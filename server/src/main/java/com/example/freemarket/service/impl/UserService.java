package com.example.freemarket.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.freemarket.dto.UserResponse;
import com.example.freemarket.model.User;
import com.example.freemarket.repository.UserRepository;
import com.example.freemarket.service.IUserService;

@Service
public class UserService implements UserDetailsService, IUserService{
    @Autowired
    UserRepository userRepository;

    public UserResponse create(User user){
        return null;
    }

    // It's actually loaded by email, but we need to override the method for spring security to work
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        String userNotFoundMsg = "User not found";
        Optional<User> user = userRepository.findByEmail(email);
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(user.get().getRole().getName()));
        try {
            return new org.springframework.security.core.userdetails.User(user.get().getEmail(), user.get().getPassword(), authorities);
        } catch (Exception e) {
            throw new UsernameNotFoundException(userNotFoundMsg);
        }
    }

    public UserDetails getByEmail(String email){
        UserDetails userDetails = this.loadUserByUsername(email);
        return userDetails;
    }
}
