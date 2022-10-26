package com.example.freemarket.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.freemarket.config.MPIntegrationConfig;
import com.example.freemarket.model.Category;
import com.example.freemarket.model.OrderDetails;
import com.example.freemarket.model.OrderItem;
import com.example.freemarket.model.Product;
import com.example.freemarket.model.Role;
import com.example.freemarket.model.User;
import com.example.freemarket.service.impl.MPIntegrationService;
import com.example.freemarket.service.impl.OrderDetailsService;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;

@ExtendWith(SpringExtension.class)
@PropertySource("classpath:application.properties")
@TestPropertySource("classpath:application.properties")
@Import(Properties.class)
public class MPIntegrationServiceTest {
    @Autowired
    IMPIntegrationService mpIntegrationService;

    @Configuration
    public static class Config {
        @Bean
        public IMPIntegrationService getMPIntegrationService() {
            return new MPIntegrationService();
        }

        @Bean
        public MPIntegrationConfig getMPIntegrationConfig() {
            return new MPIntegrationConfig();
        }
    }

    @MockBean
    OrderDetailsService orderDetailsService;

    User user;
    User buyer;
    OrderDetails orderDetails;

    @BeforeEach
    void setUp(){
        Role userRole = new Role("ROLE_USER");
        user = new User("John", "Leanon", "jleanon@email.com", "1234", userRole);
        user.setId(1L);
        buyer = new User("Pool", "MCKart", "pmckart@email.com", "2345", userRole);

        Category category = new Category("CPU");
        Product product1 = new Product("NVIDIA 3090", BigDecimal.valueOf(2199.99), user, category);
        Product product2 = new Product("YAMAHA P45", BigDecimal.valueOf(429.99), user, category);
        product1.setId(1L);
        product2.setId(2L);
        OrderItem orderItem1 = new OrderItem(product1, 4);
        OrderItem orderItem2 = new OrderItem(product2, 1);

        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem1);
        orderItems.add(orderItem2);
        BigDecimal total = orderItem1.getProduct().getPrice().multiply(BigDecimal.valueOf(orderItem1.getQuantity())).add(
                orderItem2.getProduct().getPrice().multiply(BigDecimal.valueOf(orderItem2.getQuantity()))
            );
        orderDetails = new OrderDetails(total, buyer, orderItems);
        orderDetails.setId(1L);
    }

    @Test
    void paymentRequest_validOrder_returnClient(){
        given(orderDetailsService.getActive(buyer))
            .willReturn(orderDetails);

        // Pass if no error is throw
        try {
            mpIntegrationService.checkoutRequest(buyer);
            assertTrue(true);
        } catch (MPException e) {
            System.out.println("ErrorCode: " + e.getMessage());
            e.printStackTrace();
        } catch (MPApiException e) {
            System.out.println("ErrorCode: " + e.getStatusCode());
            System.out.println("ErrorCode: " + e.getApiResponse().getContent());
            e.printStackTrace();
        }
    }
}

