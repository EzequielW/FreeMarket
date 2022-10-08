package com.example.freemarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.freemarket.model.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
    OrderDetails findAllByUserIdAndIsConfirmedFalse(Long userId);
}
