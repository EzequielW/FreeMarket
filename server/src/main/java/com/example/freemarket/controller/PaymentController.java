package com.example.freemarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.freemarket.config.MPIntegrationConfig;
import com.example.freemarket.dto.WebhookNotification;
import com.example.freemarket.service.IOrderDetailsService;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.payment.PaymentStatus;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/payments")
public class PaymentController {
    @Autowired
    MPIntegrationConfig mpIntegrationConfig;

    @Autowired
    IOrderDetailsService orderDetailsService;

    @Operation(summary="Webhook for payment notifications, used by Mercadopago API")
    @PostMapping("/notifications")
    public ResponseEntity<Object> getAll(@RequestBody WebhookNotification notification) {
        try{
            System.out.println(notification);

            if(notification.getType() == null){
                System.out.println("Notification has null status waiting for retry...");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(notification);
            }

            if(notification.getType().equals("payment")){
                PaymentClient client = new PaymentClient();
                Payment payment = client.get(notification.getData().getId(), mpIntegrationConfig.getRequestOptions());

                String status = payment.getStatus();
                Long orderDetailsId = Long.parseLong(payment.getExternalReference());

                if(status.equals(PaymentStatus.APPROVED)){
                    orderDetailsService.approvePayment(orderDetailsId);
                }
            }
        } catch(MPException e){
            System.out.println("ErrorCode: " + e.getMessage());
            e.printStackTrace();
        } catch(MPApiException e){
            System.out.println("ErrorCode: " + e.getStatusCode());
            System.out.println("ErrorCode: " + e.getApiResponse().getContent());
            e.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(notification);
        }

        return ResponseEntity.ok(notification);
    }
}
