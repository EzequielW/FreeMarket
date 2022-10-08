package com.example.freemarket.service.impl;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.freemarket.model.OrderDetails;
import com.example.freemarket.model.User;
import com.example.freemarket.repository.OrderDetailsRepository;
import com.example.freemarket.service.IOrderDetailsService;

@Service
public class OrderDetailsService implements IOrderDetailsService{
    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    public OrderDetails create(User user){
        OrderDetails orderDetails = null;
        OrderDetails currentOrder = orderDetailsRepository.findAllByUserIdAndIsConfirmedFalse(user.getId());

        if(currentOrder == null){
            orderDetails = new OrderDetails();
            orderDetails.setTotal(BigDecimal.valueOf(0));
            orderDetails.setConfirmed(false);
            orderDetails.setUser(user);
            orderDetailsRepository.save(orderDetails);
        }
        else{
            orderDetails = currentOrder;
        }

        return orderDetails;
    }

    public OrderDetails getActive(User user){
        OrderDetails currentOrder = orderDetailsRepository.findAllByUserIdAndIsConfirmedFalse(user.getId());

        // If there isn't an active order create it
        if(currentOrder == null)
        {
            currentOrder = this.create(user);
        }

        return currentOrder;
    }

    @Override
    public OrderDetails getById(Long id) {
        OrderDetails orderDetails = null;
        Optional<OrderDetails> optionalOrderDetails = orderDetailsRepository.findById(id);
        
        if(optionalOrderDetails.isPresent()){
            orderDetails = optionalOrderDetails.get();
        }

        return orderDetails;
    }   
}
