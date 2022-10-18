package com.example.freemarket.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.freemarket.dto.WebhookNotification;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/payments")
public class PaymentController {
    @Operation(summary="Web")
    @PostMapping("/notifications")
    public ResponseEntity<Object> getAll(@RequestBody WebhookNotification notification) {
        System.out.println(notification);
        System.out.println(notification.getData().toString());

        return ResponseEntity.ok(notification);
    }
}
