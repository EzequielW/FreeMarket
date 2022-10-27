package com.example.freemarket.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.freemarket.model.EnumPaymentStatus;
import com.example.freemarket.model.OrderDetails;
import com.example.freemarket.model.OrderItem;
import com.example.freemarket.model.User;
import com.example.freemarket.repository.OrderDetailsRepository;
import com.example.freemarket.service.IOrderDetailsService;

@Service
public class OrderDetailsService implements IOrderDetailsService{
    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    public OrderDetails create(User user){
        OrderDetails orderDetails = null;
        OrderDetails currentOrder = orderDetailsRepository.findAllByUserIdAndPaymentStatus(user.getId(), EnumPaymentStatus.PENDING);

        if(currentOrder == null){
            orderDetails = new OrderDetails();
            orderDetails.setTotal(BigDecimal.valueOf(0));
            orderDetails.setPaymentStatus(EnumPaymentStatus.PENDING);
            orderDetails.setUser(user);
            orderDetailsRepository.save(orderDetails);
        }
        else{
            orderDetails = currentOrder;
        }

        return orderDetails;
    }

    public OrderDetails getActive(User user){
        OrderDetails currentOrder = orderDetailsRepository.findAllByUserIdAndPaymentStatus(user.getId(), EnumPaymentStatus.PENDING);

        // If there isn't an active order create it
        if(currentOrder == null)
        {
            currentOrder = this.create(user);
        }

        return currentOrder;
    }

    public List<OrderDetails> getByUser(User user){
        List<OrderDetails> orderDetailsList = orderDetailsRepository.findAllByUserId(user.getId());
        return orderDetailsList;
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

    @Override
    public OrderDetails approvePayment(Long id) {
        OrderDetails orderDetails = getById(id);

        if(orderDetails != null){
            if(orderDetails.getPaymentStatus() != null
                && orderDetails.getPaymentStatus() != EnumPaymentStatus.REFUNDED
                && orderDetails.getPaymentStatus() != EnumPaymentStatus.CHARGED_BACK
                && orderDetails.getPaymentStatus() != EnumPaymentStatus.CANCELLED){

                BigDecimal total = BigDecimal.valueOf(0.0);
                for(OrderItem oi: orderDetails.getOrderItems()){
                    BigDecimal quantity = BigDecimal.valueOf(oi.getQuantity());
                    total.add(oi.getProduct().getPrice().multiply(quantity));
                }
                
                orderDetails.setTotal(total);
                orderDetails.setPaymentStatus(EnumPaymentStatus.APPROVED);
                orderDetailsRepository.save(orderDetails);
            }
            else{
                orderDetails = null;
            }
        }

        return orderDetails;
    }   
}
