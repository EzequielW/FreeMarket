package com.example.freemarket.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.freemarket.model.OrderDetails;
import com.example.freemarket.model.OrderItem;
import com.example.freemarket.repository.OrderDetailsRepository;
import com.example.freemarket.service.IOrderDetailsService;

@Service
public class OrderDetailsService implements IOrderDetailsService{
    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    public OrderDetails create(OrderDetails orderDetails){
        BigDecimal total = BigDecimal.ZERO;

        for(OrderItem oi: orderDetails.getOrderItems()){
            total.add(
                oi.getProduct().getPrice().multiply(BigDecimal.valueOf(oi.getQuantity()))
            );
        }
        orderDetails.setTotal(total);
        orderDetailsRepository.save(orderDetails);

        return orderDetails;
    }   
}
