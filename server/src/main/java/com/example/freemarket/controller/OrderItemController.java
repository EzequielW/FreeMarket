package com.example.freemarket.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.freemarket.dto.OrderItemRequest;
import com.example.freemarket.model.OrderItem;
import com.example.freemarket.model.Product;
import com.example.freemarket.model.User;
import com.example.freemarket.service.IOrderDetailsService;
import com.example.freemarket.service.IOrderItemService;
import com.example.freemarket.service.IProductService;
import com.example.freemarket.service.IUserService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/order_items")
public class OrderItemController {
    @Autowired
    IOrderItemService orderItemService;

    @Autowired
    IOrderDetailsService orderDetailsService;

    @Autowired
    IUserService userService;

    @Autowired
    IProductService productService;

    @Operation(summary="Add a new order item for an active order")
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid OrderItemRequest orderItemRequest, Authentication authentication) {
        OrderItem orderItem = null;
        User user = userService.getByEmail(authentication.getName());
        boolean invalidRequest = false;

        Product product = productService.getById(orderItemRequest.getProductId());
        
        if(product == null){
            invalidRequest = true;
        }
        else{
            orderItem = new OrderItem(product, orderItemRequest.getQuantity());
            orderItemService.create(user, orderItem);
        }

        if(orderItem != null){
            return ResponseEntity.ok(orderItem);
        }
        else if(invalidRequest){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Request");
        } 
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    @Operation(summary="Add a new order item for an active order")
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody @Valid OrderItemRequest orderItemRequest, Authentication authentication) {
        OrderItem orderItem = null;
        User user = userService.getByEmail(authentication.getName());
        boolean invalidRequest = false;

        Product product = productService.getById(orderItemRequest.getProductId());
        
        if(product == null){
            invalidRequest = true;
        }
        else{
            orderItem = new OrderItem(product, orderItemRequest.getQuantity());
            orderItem.setId(id);
            orderItemService.update(user, orderItem);
        }

        if(orderItem != null){
            return ResponseEntity.ok(orderItem);
        }
        else if(invalidRequest){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Request");
        } 
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    @Operation(summary="Add a new order item for an active order")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, Authentication authentication) {
        User user = userService.getByEmail(authentication.getName());

        boolean isDeleted = orderItemService.delete(user, id);

        if(isDeleted){
            return ResponseEntity.ok(isDeleted);
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }
}