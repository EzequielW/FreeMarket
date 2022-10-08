package com.example.freemarket.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemRequest {
    @Schema(description = "Product id")
    @NotNull
    private Long productId;

    @Schema(description = "Product quantity")  
    @NotNull  
    @Positive
    private int quantity;
}
