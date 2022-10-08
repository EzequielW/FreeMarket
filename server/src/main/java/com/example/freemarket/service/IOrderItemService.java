package com.example.freemarket.service;

import org.springframework.stereotype.Service;

import com.example.freemarket.model.OrderItem;
import com.example.freemarket.model.User;

@Service
public interface IOrderItemService {
    OrderItem create(User user, OrderItem orderItem);
    OrderItem update(User user, OrderItem orderItem);
    boolean delete(User user, Long id);
}
