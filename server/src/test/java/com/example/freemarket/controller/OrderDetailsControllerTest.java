package com.example.freemarket.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.freemarket.SecurityEnabledSetup;
import com.example.freemarket.dto.OrderDetailsRequest;
import com.example.freemarket.dto.OrderItemRequest;
import com.example.freemarket.model.Category;
import com.example.freemarket.model.OrderDetails;
import com.example.freemarket.model.OrderItem;
import com.example.freemarket.model.Product;
import com.example.freemarket.model.Role;
import com.example.freemarket.model.User;
import com.example.freemarket.service.IOrderDetailsService;
import com.example.freemarket.service.IProductService;

@WebMvcTest(controllers = OrderDetailsController.class)
public class OrderDetailsControllerTest extends SecurityEnabledSetup{
    // @Autowired
    // private MockMvc mockMvc;

    // @Autowired
    // private ObjectMapper mapper;

    @MockBean
    IOrderDetailsService orderDetailsService;

    @MockBean
    IProductService productService;

    User user;
    OrderDetailsRequest orderDetailsRequest;
    OrderDetails orderDetails;
    Product product1;
    Product product2;
    OrderItemRequest orderItemRequest1;
    OrderItemRequest orderItemRequest2;

    @BeforeEach
    void setUp(){
        Role role = new Role("ROLE_USER");
        user = new User("John", "Leanon", "jleanon@email.com", "1234", role);

        Category category = new Category("CPU");
        product1 = new Product("AMD 5600x", BigDecimal.valueOf(230), user, category);
        product2 = new Product("INTEL i7 7700K", BigDecimal.valueOf(230), user, category);
        orderItemRequest1 = new OrderItemRequest(1L, 5);
        orderItemRequest2 = new OrderItemRequest(2L, 3);

        Set<OrderItemRequest> orderItemsRequest = new HashSet<>();
        orderItemsRequest.add(orderItemRequest1);
        orderItemsRequest.add(orderItemRequest2);
        orderDetailsRequest = new OrderDetailsRequest(orderItemsRequest);

        OrderItem orderItem1 = new OrderItem(product1, 5);
        OrderItem orderItem2 = new OrderItem(product2, 3);
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem1);
        orderItems.add(orderItem2);
        orderDetails = new OrderDetails(null, user, orderItems);
    }

    // @Test
    // @WithMockUser(username = "jleanon@email.com", password = "1234", authorities = { "ROLE_USER" })
    // void create_validOrder_returnOk() throws Exception {
    //     given(userService.getByEmail("jleanon@email.com"))
    //         .willReturn(user);
    //     given(productService.getById(1L))
    //         .willReturn(product1);
    //     given(productService.getById(2L))
    //         .willReturn(product2);
    //     given(orderDetailsService.create(orderDetails))
    //         .willReturn(orderDetails);

    //     String json = mapper.writeValueAsString(orderDetailsRequest);
    //     mockMvc.perform(post("/order_details")
    //             .accept(MediaType.APPLICATION_JSON)
    //             .contentType(MediaType.APPLICATION_JSON)
    //             .content(json)
    //         )
    //         .andDo(print())
    //         .andExpect(status().is2xxSuccessful());
    // }
}
