package com.example.freemarket.controller;

import java.security.Principal;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.freemarket.dto.LoginRequest;
import com.example.freemarket.dto.RegistrationRequest;
import com.example.freemarket.dto.UserResponse;
import com.example.freemarket.service.IAuthService;


@RestController
@RequestMapping(path = "/auth")
public class AuthController {
    // @Autowired
    // private MessageSource messageSource;
    @Autowired
    private IAuthService authService;

    @PostMapping(path = "/register")
    public ResponseEntity<Object> register(@RequestBody RegistrationRequest registrationReqModel) {
        UserResponse userResponse = authService.register(registrationReqModel);
        if(userResponse != null){
            return ResponseEntity.ok(userResponse);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Solicitud invalida");
        }
    }
    
    // @Operation(summary="Used to stablish a session, returns a jwt.")
    // @PostMapping(value = "/login")
    // public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {
    //     ResponseEntity<?> responseEntity = authService.login(loginRequest);
    //     if(responseEntity.getStatusCode().equals(UNAUTHORIZED)){
    //         return ResponseEntity.status(UNAUTHORIZED).body(responseEntity);
    //     } else {
    //         return ResponseEntity.ok(responseEntity);
    //     }
    // }

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