package com.example.freemarket.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
	@Schema(description = "Product name", example = "AMD CPU 5600X")
    private String name;
	
	@Schema(description = "user password", example = "230")
    private BigDecimal price;
}
