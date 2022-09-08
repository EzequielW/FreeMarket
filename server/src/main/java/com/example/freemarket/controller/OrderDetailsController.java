package com.example.freemarket.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.freemarket.dto.OrderDetailsRequest;
import com.example.freemarket.dto.OrderItemRequest;
import com.example.freemarket.model.OrderDetails;
import com.example.freemarket.model.OrderItem;
import com.example.freemarket.model.Product;
import com.example.freemarket.model.User;
import com.example.freemarket.service.IOrderDetailsService;
import com.example.freemarket.service.IProductService;
import com.example.freemarket.service.IUserService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/order_details")
public class OrderDetailsController {
    @Autowired
    IOrderDetailsService orderDetailsService;

    @Autowired
    IUserService userService;

    @Autowired
    IProductService productService;

    @Operation(summary="Creates a new order of products for a user.")
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody OrderDetailsRequest orderDetailsRequest, Authentication authentication) {
        User user = userService.getByEmail(authentication.getName());
        boolean invalidRequest = false;

        OrderDetails newOrderDetails = new OrderDetails();
        newOrderDetails.setUser(user);
        Set<OrderItem> orderItems = new HashSet<>();
        for(OrderItemRequest oi: orderDetailsRequest.getOrderItemRequests()){
            Product product = productService.getById(oi.getProductId());
            if(product == null || oi.getQuantity() == 0){
                invalidRequest = true;
            }
            orderItems.add(new OrderItem(product, oi.getQuantity()));
        }
        newOrderDetails.setOrderItems(orderItems);

        OrderDetails orderDetails = null;
        if(!invalidRequest){
            orderDetails = orderDetailsService.create(newOrderDetails);
        }
        System.out.println("newOrderDetails "  + newOrderDetails);
        System.out.println("orderDetails "  + orderDetails);

        if(orderDetails != null){
            return ResponseEntity.ok(newOrderDetails);
        } 
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Request");
        }
    }
}