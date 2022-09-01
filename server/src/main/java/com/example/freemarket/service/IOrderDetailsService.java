package com.example.freemarket.service;

import org.springframework.stereotype.Service;

import com.example.freemarket.model.OrderDetails;

@Service
public interface IOrderDetailsService {
    OrderDetails create(OrderDetails orderDetails);
}
