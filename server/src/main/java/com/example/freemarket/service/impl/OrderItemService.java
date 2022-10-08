package com.example.freemarket.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.freemarket.model.OrderDetails;
import com.example.freemarket.model.OrderItem;
import com.example.freemarket.model.User;
import com.example.freemarket.repository.OrderItemRepository;
import com.example.freemarket.service.IOrderDetailsService;
import com.example.freemarket.service.IOrderItemService;

@Service
public class OrderItemService implements IOrderItemService{
    @Autowired
    IOrderDetailsService orderDetailsService;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    public OrderItem create(User user, OrderItem orderItem) {
        OrderDetails orderDetails = orderDetailsService.getActive(user);
        boolean validProduct = true;

        for(OrderItem oi: orderDetails.getOrderItems()){
            if(oi.getProduct().getId() == orderItem.getProduct().getId()){
                validProduct = false;
                break;
            }
        }

        if(validProduct){
            orderItem.setOrderDetails(orderDetails);
            orderItemRepository.save(orderItem);
        }
        else{
            orderItem = null;
        }

        return orderItem;
    }

    @Override
    public OrderItem update(User user, OrderItem newOrderItem) {
        OrderItem updatedOrderItem = null;
        Optional<OrderItem> optionalOrderItem = orderItemRepository.findById(newOrderItem.getId());

        if(optionalOrderItem.isPresent()){
            updatedOrderItem = optionalOrderItem.get();
            updatedOrderItem.setQuantity(newOrderItem.getQuantity());
            orderItemRepository.save(updatedOrderItem);
        }
        
        return updatedOrderItem;
    }

    @Override
    public boolean delete(User user, Long orderItemId) {
        boolean deleted = false;
        Optional<OrderItem> optionalOrderItem = orderItemRepository.findById(orderItemId);

        if(optionalOrderItem.isPresent()){
            OrderItem orderItem = optionalOrderItem.get();
            orderItemRepository.delete(orderItem);
            deleted = true;
        }

        return deleted;
    }
}