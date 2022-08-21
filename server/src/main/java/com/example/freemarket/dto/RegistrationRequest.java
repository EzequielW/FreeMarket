package com.example.freemarket.dto;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class RegistrationRequest {
	
    @NotNull
    @Schema(description = "User name", example = "Laura")
    private final String name;
    
    @NotNull
    @Schema(description = "User lastname", example = "Gomez")
    private final String lastname;
    
    @NonNull
    @NotNull
    @Schema(description = "Email", example = "admin20@email.com")
    private String email;
    
    @NotNull
    @Schema(description = "User password", example = "hola123")
    private final String password;
    
    @Schema(description = "User role", example = "ROLE_GUEST")
    private final String roleName;
    
}