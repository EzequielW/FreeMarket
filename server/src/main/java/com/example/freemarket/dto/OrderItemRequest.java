package com.example.freemarket.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemRequest {
    @Schema(description = "Product id")
    private Long productId;

    @Schema(description = "Product quantity")    
    private int quantity;
}
