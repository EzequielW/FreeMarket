package com.example.freemarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.freemarket.dto.LoginRequest;
import com.example.freemarket.dto.RegistrationRequest;
import com.example.freemarket.dto.UserResponse;
import com.example.freemarket.service.IAuthService;
import com.example.freemarket.service.IUserService;
import com.example.freemarket.util.JwtUtil;


@RestController
@RequestMapping(path = "/auth")
public class AuthController {
    @Autowired
    private IAuthService authService;

    @Autowired
    private IUserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(path = "/register")
    public ResponseEntity<Object> register(@RequestBody RegistrationRequest registrationReqModel) {
        UserResponse userResponse = authService.register(registrationReqModel);

        if(userResponse != null){
            return ResponseEntity.ok(userResponse);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Request");
        }
    }
    
    @PostMapping(value = "/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {
        UserResponse userResponse = authService.login(loginRequest);

        if(userResponse != null){
            UserDetails userDetails = userService.getByEmail(userResponse.getEmail());
            String jwt = jwtUtil.generateToken(userDetails);
            return ResponseEntity.ok()
                .header("Authorization", "Bearer " + jwt)
                .body(userResponse);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong username or password");
        }
    }

    // @GetMapping(value = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<?> getName(Authentication authentication, Principal principal) {
    //     try {
    //         UserResponse userDto =  authService.findByEmail(authentication.getName());
    //         return ResponseEntity.ok(userDto);
    //     }catch( NullPointerException e ){
    //         return ResponseEntity.status(HttpStatus.FORBIDDEN).body(messageSource.getMessage("error.jwt.noUserAuth", new Object[] { "jwt"}, Locale.US));
    //     }
    // }
}