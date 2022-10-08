package com.example.freemarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.freemarket.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
