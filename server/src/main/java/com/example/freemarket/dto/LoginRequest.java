package com.example.freemarket.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequest {
	
	@Schema(description = "User email", example = "usuario01@email.com")
    private String email;
	
	@Schema(description = "user password", example = "secretpassword")
    private String password;
	
}