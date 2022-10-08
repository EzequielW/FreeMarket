package com.example.freemarket.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.example.freemarket.repository.OrderItemRepository;
import com.example.freemarket.service.impl.OrderItemService;

@ExtendWith(SpringExtension.class)
public class OrderItemServiceTest {
    @Autowired
    IOrderItemService orderItemService;

    @Configuration
    public static class Config {
        @Bean
        public IOrderItemService getOrderItemService() {
            return new OrderItemService();
        }
    }

    @MockBean
    OrderItemRepository orderItemRepository;

    @MockBean
    IOrderDetailsService orderDetailsService;

    User user;
    User buyer;
    OrderDetails orderDetails;
    OrderItem orderItem;

    @BeforeEach
    void setUp(){
        Role userRole = new Role("ROLE_USER");
        user = new User("John", "Leanon", "jleanon@email.com", "1234", userRole);
        buyer = new User("Pool", "MCKart", "pmckart@email.com", "2345", userRole);

        Category category = new Category("CPU");
        Product product1 = new Product("NVIDIA 3090", BigDecimal.valueOf(2200), user, category);
        orderItem = new OrderItem(product1, 4);

        List<OrderItem> orderItems = new ArrayList<>();

        orderDetails = new OrderDetails();
        orderDetails.setUser(user);
        orderDetails.setTotal(BigDecimal.valueOf(0));
        orderDetails.setOrderItems(orderItems);
    }

    @Test
    void create_validOrderItem_returnOrderItem(){
        given(orderDetailsService.getActive(user))
            .willReturn(orderDetails);

        assertEquals(orderItem, orderItemService.create(user, orderItem));
    }

    @Test
    void update_validOrderItem_returnOrderItem(){
        Optional<OrderItem> optional = Optional.of(orderItem);
        given(orderItemRepository.findById(orderItem.getId()))
            .willReturn(optional);

        assertEquals(orderItem, orderItemService.update(user, orderItem));
    }

    @Test
    void delete_validId_returnOrderItem(){
        Optional<OrderItem> optional = Optional.of(orderItem);
        given(orderItemRepository.findById(orderItem.getId()))
            .willReturn(optional);

        assertEquals(true, orderItemService.delete(user, orderItem.getId()));
    }
}
