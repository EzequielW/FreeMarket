package com.example.freemarket.service;

import org.springframework.stereotype.Service;

import com.example.freemarket.model.OrderDetails;
import com.example.freemarket.model.User;

@Service
public interface IOrderDetailsService {
    OrderDetails create(User user);
    OrderDetails getActive(User user);
    OrderDetails getById(Long id);
    OrderDetails approvePayment(Long id);
}
