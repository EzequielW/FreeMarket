package com.example.freemarket.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest{
	@Schema(description = "Product name", example = "AMD CPU 5600X")
    @Size(min = 10, max = 120)
    private String name;
	
	@Schema(description = "Product price", example = "230")
    @Digits(integer = 6, fraction = 2)
    private BigDecimal price;

    @Schema(description = "Category this product belongs to", example = "1")
    private Long categoryId;

    private MultipartFile file;
}
