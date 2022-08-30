package com.example.freemarket.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
	
	@Schema(description = "User email", example = "admin@email.com")
    private String email;
	
	@Schema(description = "user password", example = "1234")
    private String password;
	
}