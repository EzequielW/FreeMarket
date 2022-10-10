package com.example.freemarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.freemarket.model.OrderDetails;
import com.example.freemarket.model.User;
import com.example.freemarket.service.IMPIntegrationService;
import com.example.freemarket.service.IOrderDetailsService;
import com.example.freemarket.service.IProductService;
import com.example.freemarket.service.IUserService;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;

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

    @Autowired
    IMPIntegrationService mpIntegrationService;

    // @Operation(summary="Creates a new order of products for a user")
    // @PostMapping
    // public ResponseEntity<Object> create(@RequestBody OrderDetailsRequest orderDetailsRequest, Authentication authentication) {
    //     User user = userService.getByEmail(authentication.getName());
    //     boolean invalidRequest = false;

    //     OrderDetails newOrderDetails = new OrderDetails();
    //     newOrderDetails.setUser(user);
    //     Set<OrderItem> orderItems = new HashSet<>();
    //     for(OrderItemRequest oi: orderDetailsRequest.getOrderItemRequests()){
    //         Product product = productService.getById(oi.getProductId());
    //         if(product == null || oi.getQuantity() == 0){
    //             invalidRequest = true;
    //         }
    //         orderItems.add(new OrderItem(product, oi.getQuantity()));
    //     }
    //     newOrderDetails.setOrderItems(orderItems);

    //     OrderDetails orderDetails = null;
    //     if(!invalidRequest){
    //         orderDetails = orderDetailsService.create(newOrderDetails);
    //     }

    //     if(orderDetails != null){
    //         return ResponseEntity.ok(newOrderDetails);
    //     } 
    //     else if(invalidRequest){
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Request");
    //     }
    //     else {
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
    //     }
    // }

    @Operation(summary="Get user active order details")
    @GetMapping("/active")
    public ResponseEntity<Object> create(Authentication authentication) {
        User user = userService.getByEmail(authentication.getName());

        OrderDetails orderDetails = orderDetailsService.getActive(user);

        if(orderDetails != null){
            return ResponseEntity.ok(orderDetails);
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    @Operation(summary="Checkout user active order")
    @PostMapping("/checkout")
    public ResponseEntity<Object> checkout(Authentication authentication) {
        User user = userService.getByEmail(authentication.getName());

        String preferenceId;
        try {
            preferenceId = mpIntegrationService.checkoutRequest(user);
            return ResponseEntity.ok(preferenceId);
        } catch (MPException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not process request");
        } catch (MPApiException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not process request");
        }
    }
}