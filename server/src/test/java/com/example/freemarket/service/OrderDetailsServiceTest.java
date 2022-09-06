package com.example.freemarket.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.freemarket.model.Category;
import com.example.freemarket.model.OrderDetails;
import com.example.freemarket.model.OrderItem;
import com.example.freemarket.model.Product;
import com.example.freemarket.model.Role;
import com.example.freemarket.model.User;
import com.example.freemarket.repository.OrderDetailsRepository;
import com.example.freemarket.service.impl.OrderDetailsService;

@ExtendWith(SpringExtension.class)
public class OrderDetailsServiceTest {
    @Autowired
    IOrderDetailsService orderDetailsService;

    @Configuration
    public static class Config {
        @Bean
        public IOrderDetailsService getOrderDetailsService() {
            return new OrderDetailsService();
        }
    }

    @MockBean
    OrderDetailsRepository orderDetailsRepository;

    User user;
    User buyer;
    OrderDetails orderDetails;

    @BeforeEach
    void setUp(){
        Role userRole = new Role("ROLE_USER");
        user = new User("John", "Leanon", "jleanon@email.com", "1234", userRole);
        buyer = new User("Pool", "MCKart", "pmckart@email.com", "2345", userRole);

        Category category = new Category("CPU");
        Product product1 = new Product("NVIDIA 3090", BigDecimal.valueOf(2200), user, category);
        Product product2 = new Product("YAMAHA P45", BigDecimal.valueOf(410), user, category);
        OrderItem orderItem1 = new OrderItem(product1, 4);
        OrderItem orderItem2 = new OrderItem(product2, 1);

        Set<OrderItem> orderItems = new HashSet<>();
        orderItems.add(orderItem1);
        orderItems.add(orderItem2);
        orderDetails = new OrderDetails(BigDecimal.valueOf(9210), buyer, orderItems);
    }

    @Test
    void create_validaOrder_returnOrder(){
        assertEquals(orderDetails, orderDetailsService.create(orderDetails));
    }
}
