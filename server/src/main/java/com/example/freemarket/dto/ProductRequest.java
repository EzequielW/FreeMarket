package com.example.freemarket.dto;

import java.math.BigDecimal;

import com.example.freemarket.model.Category;

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
	
	@Schema(description = "Product price", example = "230")
    private BigDecimal price;

    @Schema(description = "Category this product belongs to", example = "230")
    private Category category;
}
