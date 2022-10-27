package com.example.freemarket.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.freemarket.model.OrderDetails;
import com.example.freemarket.model.User;

@Service
public interface IOrderDetailsService {
    OrderDetails create(User user);
    OrderDetails getActive(User user);
    List<OrderDetails> getByUser(User user);
    OrderDetails getById(Long id);
    OrderDetails approvePayment(Long id);
}
