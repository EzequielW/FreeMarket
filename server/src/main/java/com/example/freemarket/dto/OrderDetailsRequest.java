package com.example.freemarket.dto;

import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsRequest {
    @Schema(description = "List of order details with product and quantity")
    private Set<OrderItemRequest> orderItemRequests;
}
